package de.saringer.moviedemoapp.features.login.network

import de.saringer.moviedemoapp.BuildConfig
import de.saringer.moviedemoapp.features.login.network.model.LoginGuestSessionRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginSessionIdUserRemote
import de.saringer.moviedemoapp.features.login.network.model.LoginTokenRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApi {

    // region login
    @GET("authentication/token/new?api_key=${BuildConfig.APIKEY}")
    suspend fun getRequestToken(): LoginTokenRemote

    @POST("authentication/guest_session/new?api_key=${BuildConfig.APIKEY}")
    suspend fun getSessionIdForGuests(): LoginGuestSessionRemote

    @POST("authentication/token/validate_with_login?api_key=${BuildConfig.APIKEY}")
    suspend fun getSessionIdWithUserData(
        @Query("request_token") requestToken: String,
        @Query("username") username: String,
        @Query("password") password: String,
    ): LoginSessionIdUserRemote

    @POST("authentication/session?api_key=${BuildConfig.APIKEY}")
    suspend fun deleteSession(
        @Query("session_id") sessionId: String
    ) : Response<Boolean>

    // endregion
}