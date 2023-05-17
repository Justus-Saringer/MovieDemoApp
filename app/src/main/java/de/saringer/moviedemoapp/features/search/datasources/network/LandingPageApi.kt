package de.saringer.moviedemoapp.features.search.datasources.network

import de.saringer.moviedemoapp.BuildConfig
import de.saringer.moviedemoapp.features.search.datasources.network.model.discover.DiscoverResponse
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviecredits.MovieCreditsResponse
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails.MovieDetailsResponse
import de.saringer.moviedemoapp.features.search.datasources.network.model.persondetails.PersonDetailsRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//    https://developers.themoviedb.org/3/discover/movie-discover
//    https://developers.themoviedb.org/3/movies/get-movie-details
interface LandingPageApi {

    @GET("/3/movie/{movie_id}?api_key=${BuildConfig.APIKEY}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetailsResponse

    @GET("/3/movie/{movie_id}/credits?api_key=${BuildConfig.APIKEY}")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
    ): MovieCreditsResponse

    @GET("/3/person/{person_id}?api_key=${BuildConfig.APIKEY}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int
    ): PersonDetailsRemote

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey: String? = BuildConfig.APIKEY,
        @Query("page") page: Int,
    ): DiscoverResponse

    @GET("discover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc")
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