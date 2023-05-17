package de.saringer.moviedemoapp.features.search.datasources.network

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits.MovieCredits
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.MovieDetails
import de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails.PersonDetails
import de.saringer.moviedemoapp.features.search.datasources.network.extension.toMovieCredits
import de.saringer.moviedemoapp.features.search.datasources.network.extension.toMovieDetails
import de.saringer.moviedemoapp.features.search.datasources.network.extension.toPersonDetails
import de.saringer.moviedemoapp.features.search.datasources.pagingsources.BestMoviesFromYearPagingSource
import de.saringer.moviedemoapp.features.search.datasources.pagingsources.HighRatedMoviesPagingSource
import de.saringer.moviedemoapp.features.search.datasources.pagingsources.MostPopularMoviesPagingSource
import javax.inject.Inject

class LandingPageRepository @Inject constructor(
    private val landingPageApi: LandingPageApi
) {

    fun getMostPopularMovies() = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { MostPopularMoviesPagingSource(landingPageApi = landingPageApi) }
    ).flow

    fun getHighRatedMovies() = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { HighRatedMoviesPagingSource(landingPageApi = landingPageApi) }
    ).flow

    fun getBestMoviesOfTheYear(year: Int) = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { BestMoviesFromYearPagingSource(landingPageApi = landingPageApi, year = year) }
    ).flow

//    fun getHighRatedScienceFictionMovies(year: Int) = Pager(
//        config = PagingConfig(pageSize = 15),
//        pagingSourceFactory = { HighratedScienceFictionMoviesPagingSource(landingPageApi, year) }
//    )

    suspend fun getMovieDetails(movieId: Int): MovieDetails? {
        return runCatching {
            landingPageApi.getMovieDetails(movieId = movieId).toMovieDetails()
        }
            .onFailure {
                Log.i("MOVIEAPP", "api error occured", it)
            }
            .getOrNull()
    }

    suspend fun getMovieCredits(movieId: Int): MovieCredits? {
        return runCatching {
            landingPageApi.getMovieCredits(movieId = movieId).toMovieCredits()
        }.getOrNull()
    }

    suspend fun getPersonDetails(personId: Int): PersonDetails? {
        return kotlin.runCatching {
            landingPageApi.getPersonDetails(personId).toPersonDetails()
        }.getOrNull()
    }

}