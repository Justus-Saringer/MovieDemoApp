package de.saringer.moviedemoapp.features.search.network

import de.saringer.moviedemoapp.BuildConfig
import de.saringer.moviedemoapp.features.search.network.model.DiscoverResponse
import retrofit2.http.GET
import retrofit2.http.Query

//    https://developers.themoviedb.org/3/discover/movie-discover
interface LandingPageApi {
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey: String? = BuildConfig.APIKEY,
        @Query("page") page: Int,
    ): DiscoverResponse

    @GET("discover/movie/?certification_country=US&certification=R&sort_by=vote_average.descdiscover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc")
    suspend fun getHighRatedMovies(
        @Query("api_key") apiKey: String? = BuildConfig.APIKEY,
        @Query("page") page: Int,
    ): DiscoverResponse

    @GET("discover/movie?sort_by=vote_average.desc")
    suspend fun getBestMoviesFromYear(
        @Query("api_key") apiKey: String? = BuildConfig.APIKEY,
        @Query("primary_release_year") releaseYear: Int,
        @Query("page") page: Int,
    ): DiscoverResponse

    @GET("discover/movie?with_genres=878&sort_by=vote_average.desc")
    suspend fun getHighestRatedScienceFictionMovies(
        @Query("api_key") apiKey: String? = BuildConfig.APIKEY,
        @Query("page") page: Int,
    ): DiscoverResponse
}