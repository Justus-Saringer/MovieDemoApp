package de.saringer.moviedemoapp.features.login


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginTokenRemote(
    @SerialName("expires_at")
    val expiresAt: String? = null,
    @SerialName("request_token")
    val requestToken: String? = null,
    @SerialName("success")
    val success: Boolean? = null
)