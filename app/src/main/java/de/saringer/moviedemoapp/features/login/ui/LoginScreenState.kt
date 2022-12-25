package de.saringer.moviedemoapp.features.login.ui

import androidx.compose.runtime.mutableStateOf

class LoginScreenState constructor(
    usernameInput: String,
    passwordInput: String,
    isLoading: Boolean,
    isPasswordVisible: Boolean,
    hasError: Boolean = false,
) {

    val usernameInput = mutableStateOf(usernameInput)
    val passwordInput = mutableStateOf(passwordInput)
    val isLoading = mutableStateOf(isLoading)
    val isPasswordVisible = mutableStateOf(isPasswordVisible)
    val hasError = mutableStateOf(hasError)
}