package de.saringer.moviedemoapp.features.login

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.features.login.ui.LoginScreenState
import de.saringer.moviedemoapp.shared.extensions.observeConnectivityAsFlow
import de.saringer.moviedemoapp.shared.state.ConnectionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val application: Application
) : ViewModel() {

    val loginState = LoginScreenState(
        usernameInput = mutableStateOf(""),
        passwordInput = mutableStateOf(""),
        isLoading = mutableStateOf(false),
        isPasswordVisible = mutableStateOf(false)
    )

    val getSessionIdUser
        get() = loginRepository.sessionIdUser

    val getSessionIdGuest
        get() = loginRepository.sessionIdGuest

    init {
        viewModelScope.launch {
            loginRepository.tokenData = loginRepository.getTokenData()
            if (hasStoredLoginData() && loginRepository.tokenData?.success == true) {
                loginRepository.sessionIdUser = loginRepository.getSessionIdWithUserData(
                    username = loginRepository.getStoredUsername().orEmpty(),
                    password = loginRepository.getStoredPassword().orEmpty(),
                    requestToken = loginRepository.tokenData?.requestToken!!
                )
            }

            setListener()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun setListener() {
        application.applicationContext.observeConnectivityAsFlow().collect {
            loginState.isInternetAvailable.value = it == ConnectionState.Available
        }
    }

    private fun hasStoredLoginData(): Boolean {
        val username = loginRepository.getStoredUsername()
        val password = loginRepository.getStoredPassword()
        return !(username.isNullOrEmpty() || password.isNullOrEmpty())
    }

    fun loginAsGuest(invokeAfter: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            if (loginRepository.tokenData == null || loginRepository.tokenData?.success == false) loginRepository.getTokenData()
            loginRepository.sessionIdGuest = loginRepository.getSessionIdForGuests()

            loginState.isLoading.value = false
            invokeAfter()
        }
    }

    fun loginAsUser(invokeAfter: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            if (loginRepository.tokenData == null || loginRepository.tokenData?.success == false) loginRepository.getTokenData()

            loginRepository.sessionIdUser = loginRepository.tokenData?.requestToken?.let { requestToken ->
                loginRepository.getSessionIdWithUserData(
                    username = loginState.usernameInput.value.orEmpty(),
                    password = loginState.passwordInput.value.orEmpty(),
                    requestToken = requestToken
                )
            }

            loginState.isLoading.value = false
            invokeAfter()
        }
    }
}