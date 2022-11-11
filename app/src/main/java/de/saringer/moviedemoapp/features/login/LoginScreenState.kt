package de.saringer.moviedemoapp.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class LoginScreenState private constructor(
    usernameInput: String,
    passwordInput: String,
    isLoading: Boolean,
    isPasswordVisible: Boolean
) {

    val usernameInput = mutableStateOf(usernameInput)
    val passwordInput = mutableStateOf(passwordInput)
    val isLoading = mutableStateOf(isLoading)
    val isPasswordVisible = mutableStateOf(isPasswordVisible)

    companion object {
        @Composable
        fun rememberState(
            usernameInput: String = "",
            passwordInput: String = "",
            isLoading: Boolean = false,
            isPasswordVisible: Boolean = false
        ) = remember {
            LoginScreenState(
                usernameInput = usernameInput,
                passwordInput = passwordInput,
                isLoading= isLoading,
                isPasswordVisible = isPasswordVisible,
            )
        }
    }
}