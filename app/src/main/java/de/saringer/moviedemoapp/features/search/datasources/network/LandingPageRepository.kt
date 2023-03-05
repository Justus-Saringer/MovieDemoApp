package de.saringer.moviedemoapp.features.search.datasources.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import de.saringer.moviedemoapp.features.search.datasources.pagingsources.MostPopularMoviesPagingSource
import javax.inject.Inject

class LandingPageRepository @Inject constructor(
    private val landingPageApi: LandingPageApi
) {

    fun getMostPopularMovies() = Pager(
        config = PagingConfig(pageSize = 15),
        pagingSourceFactory = { MostPopularMoviesPagingSource(landingPageApi) }
    ).flow


    // not needed due to pagination
/*    suspend fun getMostPopularMovies(page: Int): DiscoverModel? {
        return withContext(Dispatchers.IO) {
            runCatching {
                landingPageApi.getMostPopularMovies(page = page).toDiscoverModel()
            }.getOrNull()
        }
    }

    suspend fun getHighRatedMovies(page: Int): DiscoverModel? {
        return withContext(Dispatchers.IO) {
            runCatching {
                landingPageApi.getHighRatedMovies(page = page).toDiscoverModel()
            }.getOrNull()
        }
    }

    suspend fun getBestMoviesFromYear(page: Int, releaseYear: Int): DiscoverModel? {
        return withContext(Dispatchers.IO) {
            runCatching {
                landingPageApi.getBestMoviesFromYear(releaseYear = releaseYear, page = page).toDiscoverModel()
            }.getOrNull()
        }
    }

    suspend fun getHighestRatedScienceFictionMovies(page: Int): DiscoverModel? {
        return withContext(Dispatchers.IO) {
            runCatching {
                landingPageApi.getHighestRatedScienceFictionMovies(page = page).toDiscoverModel()
            }.getOrNull()
        }
    }*/
}