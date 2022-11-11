package de.saringer.moviedemoapp.features

import retrofit2.http.GET

interface MovieApi {
     // region login

        @GET("/authentication/token/new")
        suspend fun getRequestToken()

    // endregion
}