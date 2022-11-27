package de.saringer.moviedemoapp.features.login.network

import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: LoginApi
) {
    suspend fun getRequestToken() = api.getRequestToken()

    suspend fun getSessionIdForGuests() = api.getSessionIdForGuests()

    suspend fun getSessionIdWithUserData(
        username : String,
        password : String,
        requestToken: String
    ) = api.getSessionIdWithUserData(
        username = username, password = password, requestToken = requestToken
    )

    suspend fun deleteSession() = api.deleteSession()
}