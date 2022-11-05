package de.saringer.moviedemoapp.features

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class LoginScreenState private constructor(
    usernameInput: String,
    passwordInput: String,
    isLoading: Boolean,
) {

    val usernameInput = mutableStateOf(usernameInput)
    val passwordInput = mutableStateOf(passwordInput)
    val isLoading = mutableStateOf(isLoading)

    companion object {
        @Composable
        fun rememberState(
            usernameInput: String = "",
            passwordInput: String = "",
            isLoading: Boolean = false
        ) = remember {
            LoginScreenState(
                usernameInput = usernameInput,
                passwordInput = passwordInput,
                isLoading= isLoading,
            )
        }
    }
}