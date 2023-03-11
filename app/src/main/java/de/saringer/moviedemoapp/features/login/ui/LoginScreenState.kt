package de.saringer.moviedemoapp.features.login.ui

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf

class LoginScreenState constructor(
    usernameInput: String,
    passwordInput: String,
    isLoading: Boolean,
    isPasswordVisible: Boolean,
) {
    val usernameInput = mutableStateOf(usernameInput)
    val passwordInput = mutableStateOf(passwordInput)
    val isLoading = mutableStateOf(isLoading)
    val isPasswordVisible = mutableStateOf(isPasswordVisible)
    val snackBarHostState = SnackbarHostState()
    val isInternetAvailable = mutableStateOf(true)

    private val areInputFieldsEmpty: Boolean
        get() = usernameInput.value.isEmpty() || passwordInput.value.isEmpty()

    val hasPageError: Boolean
        get() = areInputFieldsEmpty || isInternetAvailable.value

    val errorText: String
        get() = when {
            isInternetAvailable.value -> "No Internet available"
            usernameInput.value.isEmpty() -> "Username is empty"
            passwordInput.value.isEmpty() -> "Password is empty"
            else -> "Password or Username is incorrect"
        }
}