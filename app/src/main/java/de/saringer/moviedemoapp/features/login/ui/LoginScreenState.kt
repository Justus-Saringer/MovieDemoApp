package de.saringer.moviedemoapp.features.login.ui

import androidx.compose.runtime.mutableStateOf

class LoginScreenState constructor(
    usernameInput: String,
    passwordInput: String,
    isLoading: Boolean,
    isPasswordVisible: Boolean
) {

    val usernameInput = mutableStateOf(usernameInput)
    val passwordInput = mutableStateOf(passwordInput)
    val isLoading = mutableStateOf(isLoading)
    val isPasswordVisible = mutableStateOf(isPasswordVisible)
}