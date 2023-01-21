package de.saringer.moviedemoapp.features.login.network.extension

import de.saringer.moviedemoapp.features.login.network.domain.LoginSessionIdUser
import de.saringer.moviedemoapp.features.login.network.domain.LoginToken
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdUserRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginTokenRemote

fun LoginTokenRemote.toLoginToken() = LoginToken(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success ?: false,
)

fun LoginSessionIdUserRemote.toLoginSessionIdUser() = LoginSessionIdUser(
    sessionIdUser = sessionId,
    success = success ?: false,
    expiresAt = expiresAt
)