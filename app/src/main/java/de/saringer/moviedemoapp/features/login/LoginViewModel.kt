package de.saringer.moviedemoapp.features.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.features.login.ui.LoginScreenState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginRepository: LoginRepository
): ViewModel() {

    val loginState = LoginScreenState(usernameInput = "", passwordInput = "", isLoading = false, isPasswordVisible = false)

    init {

    }
}