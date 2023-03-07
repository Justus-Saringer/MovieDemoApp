package de.saringer.moviedemoapp.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.search.datasources.network.LandingPageRepository
import de.saringer.moviedemoapp.features.search.datasources.network.domain.discover.Movie
import de.saringer.moviedemoapp.features.search.datasources.network.extension.toMovieDetails
import de.saringer.moviedemoapp.features.search.ui.MovieDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val landingPageRepository: LandingPageRepository
) : ViewModel() {

    val movieDetailsState = MovieDetailsState(
        refresh = { movieId -> getMovieDetails(movieId) }
    )

    fun getMostPopularMovies(): Flow<PagingData<Movie>> = landingPageRepository.getMostPopularMovies().cachedIn(viewModelScope)

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailsState.movieDetails.value = null
            movieDetailsState.movieDetails.value = landingPageRepository.getMovieDetails(movieId = movieId)?.toMovieDetails()
        }
    }
}