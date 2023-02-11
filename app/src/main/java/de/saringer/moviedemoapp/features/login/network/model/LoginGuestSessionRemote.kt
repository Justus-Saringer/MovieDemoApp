package de.saringer.moviedemoapp.features.login.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginGuestSessionRemote(
    @SerialName("expires_at")
    val expiresAt: String? = null,
    @SerialName("guest_session_id")
    val guestSessionId: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)