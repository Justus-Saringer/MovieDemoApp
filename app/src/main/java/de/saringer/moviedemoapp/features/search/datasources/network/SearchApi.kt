package de.saringer.moviedemoapp.features.search.datasources.network

import de.saringer.moviedemoapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    // TODO: create Responses
    @GET("search/companies?api_key=${BuildConfig.APIKEY}")
    suspend fun searchForCompanies(
        @Query("page") page: Int,
        @Query("query") query: String
    )

    @GET("search/tv?api_key=${BuildConfig.APIKEY}")
    suspend fun searchForTvShows(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true
    )

    @GET("search/person?api_key=${BuildConfig.APIKEY}")
    suspend fun searchForPerson(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true
    )

    @GET("search/movie?api_key=${BuildConfig.APIKEY}")
    suspend fun searchForMovies(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true
    )

    @GET("search/multi?api_key=${BuildConfig.APIKEY}")
    suspend fun multiSearch(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true
    )

    @GET("search/keyword?api_key=${BuildConfig.APIKEY}")
    suspend fun keywordSearch(
        @Query("page") page: Int,
        @Query("query") query: String,
    )

    @GET("search/collection?api_key=${BuildConfig.APIKEY}")
    suspend fun searchForCollections(
        @Query("page") page: Int,
        @Query("query") query: String,
    )
}