package de.saringer.moviedemoapp.features.login.network.domain

data class LoginToken(
    val expiresAt: String? = null,
    val requestToken: String? = null,
    val success: Boolean
)