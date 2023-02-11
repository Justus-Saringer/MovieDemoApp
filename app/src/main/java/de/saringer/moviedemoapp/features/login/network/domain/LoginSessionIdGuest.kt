package de.saringer.moviedemoapp.features.login.network.domain

data class LoginSessionIdGuest(
    val expiresAt: String? = null,
    val guestSessionId: String? = null,
    val success: Boolean
)