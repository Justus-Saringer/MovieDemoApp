package de.saringer.moviedemoapp.features.login.network.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LoginSessionIdUserRemote(
    @SerialName("expires_at")
    val expiresAt: String? = null,
    @SerialName("request_token")
    val sessionId: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)