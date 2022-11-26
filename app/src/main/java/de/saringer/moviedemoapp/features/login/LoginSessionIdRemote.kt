package de.saringer.moviedemoapp.features.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginSessionIdRemote(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("session_id")
    val sessionId: String? = null
)