package de.saringer.moviedemoapp.features.login.network.domain

data class LoginSessionIdUser(
    val expiresAt: String? = null,
    val sessionIdUser: String? = null,
    val success: Boolean
)