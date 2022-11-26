package de.saringer.moviedemoapp.features.login.network

import de.saringer.moviedemoapp.BuildConfig
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdUserRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginTokenRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    // region login

    @GET("/authentication/token/new?api_key=${BuildConfig.APIKEY}")
    suspend fun getRequestToken(): Response<LoginTokenRemote>

    @POST("/authentication/session/new?api_key=${BuildConfig.APIKEY}")
    suspend fun getSessionIdForGuests()

    @POST("/authentication/token/validate_with_login")
    suspend fun getSessionIdWithUserData(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("request_token") sessionId: String
    ): Response<LoginSessionIdUserRemote>

    // TODO: move to correct place
    @POST("/authentication/session")
    suspend fun deleteSession() : Response<Boolean>

    // endregion
}