package de.saringer.moviedemoapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.features.login.network.domain.LoginSessionIdUser
import de.saringer.moviedemoapp.features.login.network.domain.LoginToken
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdUserRemote
import de.saringer.moviedemoapp.features.login.ui.LoginScreenState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginState = LoginScreenState(usernameInput = "", passwordInput = "", isLoading = false, isPasswordVisible = false)

    var tokenData: LoginToken? = null
    var sessionIdGuest: LoginSessionIdRemote? = null
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

/*
    fun getToken() = viewModelScope.launch(Dispatchers.IO) {
        try {
            tokenData = loginRepository.getRequestToken().body()
        } catch (e: Exception) {
            loginState
        }
    }
*/

    // if yes, use it and get a token and then getting a session with the given data


    /*withContext(Dispatchers.IO) {
    tokenData = loginRepository.getRequestToken().body()
}*/
}