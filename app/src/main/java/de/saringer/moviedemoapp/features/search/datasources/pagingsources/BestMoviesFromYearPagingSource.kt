package de.saringer.moviedemoapp.features.search.datasources.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import de.saringer.moviedemoapp.features.search.datasources.network.LandingPageApi
import de.saringer.moviedemoapp.features.search.datasources.network.domain.discover.Movie
import de.saringer.moviedemoapp.features.search.datasources.network.extension.toDiscoverModel
import java.util.concurrent.CancellationException

class BestMoviesFromYearPagingSource(
    private val landingPageApi: LandingPageApi,
    private val year: Int
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = landingPageApi.getBestMoviesFromYear(page = page, releaseYear = year).toDiscoverModel()

            LoadResult.Page(
                data = response?.results ?: emptyList(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response?.results?.isEmpty() == true) null else page.plus(1)
            )
        } catch (ce: CancellationException) {
            throw ce
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}