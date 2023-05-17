package de.saringer.moviedemoapp.features.login.ui

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginScreenState constructor(
    val usernameInput: MutableState<String?> = mutableStateOf(null),
    val passwordInput: MutableState<String?> = mutableStateOf(null),
    val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val isPasswordVisible: MutableState<Boolean> = mutableStateOf(false),
    val isInternetAvailable: MutableState<Boolean> = mutableStateOf(false),
    val snackBarHostState: SnackbarHostState = SnackbarHostState()
) {

    private val areInputFieldsEmpty: Boolean
        get() = usernameInput.value.isNullOrEmpty() || passwordInput.value.isNullOrEmpty()

    val hasPageError: Boolean
        get() = areInputFieldsEmpty || isInternetAvailable.value ?: false

    val errorText: String
        get() = when {
            isInternetAvailable.value == false -> "No Internet available"
            usernameInput.value.isNullOrEmpty() -> "Username is empty"
            passwordInput.value.isNullOrEmpty() -> "Password is empty"
            else -> "Password or Username is incorrect"
        }
}