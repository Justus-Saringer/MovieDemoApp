package de.saringer.moviedemoapp.features.search.network

import de.saringer.moviedemoapp.features.search.network.domain.DiscoverModel
import de.saringer.moviedemoapp.features.search.network.extension.toDiscoverModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LandingPageRepository @Inject constructor(
    private val landingPageApi: LandingPageApi
) {

    suspend fun getMostPopularMovies(page: Int): DiscoverModel? {
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
    }
}