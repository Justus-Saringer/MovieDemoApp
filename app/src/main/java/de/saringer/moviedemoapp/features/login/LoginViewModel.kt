package de.saringer.moviedemoapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.features.login.network.domain.LoginSessionIdGuest
import de.saringer.moviedemoapp.features.login.network.domain.LoginSessionIdUser
import de.saringer.moviedemoapp.features.login.network.domain.LoginToken
import de.saringer.moviedemoapp.features.login.ui.LoginScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginState = LoginScreenState(usernameInput = "", passwordInput = "", isLoading = false, isPasswordVisible = false)

    var tokenData: LoginToken? = null
    var sessionIdGuest: LoginSessionIdGuest? = null
    var sessionIdUser: LoginSessionIdUser? = null

    init {
        viewModelScope.launch {
            tokenData = loginRepository.getTokenData()
            if (hasStoredLoginData() && tokenData?.success == true) {
                sessionIdUser = loginRepository.getSessionIdWithUserData(
                    username = loginRepository.getStoredUsername().orEmpty(),
                    password = loginRepository.getStoredPassword().orEmpty(),
                    requestToken = tokenData?.requestToken!!
                )
            }
        }
    }

    private fun hasStoredLoginData(): Boolean {
        val username = loginRepository.getStoredUsername()
        val password = loginRepository.getStoredPassword()
        return !(username.isNullOrEmpty() || password.isNullOrEmpty())
    }

    fun loginAsGuest() {
        viewModelScope.launch(Dispatchers.IO) {
            if (tokenData == null || tokenData?.success == false) loginRepository.getTokenData()
            sessionIdGuest = loginRepository.getSessionIdForGuests()

            loginState.isLoading.value = false
        }
    }

    fun loginAsUser() {
        viewModelScope.launch(Dispatchers.IO) {
            if (tokenData == null || tokenData?.success == false) loginRepository.getTokenData()

            sessionIdUser = tokenData?.requestToken?.let { requestToken ->
                loginRepository.getSessionIdWithUserData(
                    username = loginState.usernameInput.value,
                    password = loginState.passwordInput.value,
                    requestToken = requestToken
                )
            }

            loginState.isLoading.value = false
        }
    }
}