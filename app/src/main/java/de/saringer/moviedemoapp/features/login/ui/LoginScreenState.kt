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

    private val areInputFieldsEmpty: Boolean
        get() = usernameInput.value.isEmpty() || passwordInput.value.isEmpty()

    val hasPageError: Boolean
        get() = areInputFieldsEmpty

    val errorText: String
        get() = when {
            usernameInput.value.isEmpty() -> "Username is empty"
            passwordInput.value.isEmpty() -> "Password is empty"
            else -> "Password or Username is incorrect"
        }
}