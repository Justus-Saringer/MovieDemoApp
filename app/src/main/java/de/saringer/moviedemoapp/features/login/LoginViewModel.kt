package de.saringer.moviedemoapp.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdUserRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginTokenRemote
import de.saringer.moviedemoapp.features.login.ui.LoginScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    val loginState = LoginScreenState(usernameInput = "", passwordInput = "", isLoading = false, isPasswordVisible = false)


    var tokenData: LoginTokenRemote? = null
    var sessionIdGuest: LoginSessionIdRemote? = null
    var sessionIdUser: LoginSessionIdUserRemote? = null

    fun getToken() = viewModelScope.launch(Dispatchers.IO) {
        try {
            tokenData = loginRepository.getRequestToken().body()
        } catch (e: Exception) {
            loginState
        }
    }

    /*withContext(Dispatchers.IO) {
    tokenData = loginRepository.getRequestToken().body()
}*/
}