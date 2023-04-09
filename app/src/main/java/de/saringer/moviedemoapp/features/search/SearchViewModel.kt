package de.saringer.moviedemoapp.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.search.datasources.network.LandingPageRepository
import de.saringer.moviedemoapp.features.search.datasources.network.domain.discover.Movie
import de.saringer.moviedemoapp.features.search.moviedetails.MovieDetailsState
import de.saringer.moviedemoapp.features.search.persondetails.PersonDetailsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val landingPageRepository: LandingPageRepository
) : ViewModel() {

    val movieDetailsState = MovieDetailsState(
        refresh = { movieId -> getMovieDetailsWithCredits(movieId) }
    )

    val personDetailsState = PersonDetailsState()

    fun getMostPopularMovies(): Flow<PagingData<Movie>> = landingPageRepository.getMostPopularMovies().cachedIn(viewModelScope)

    fun getMovieDetailsWithCredits(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieDetailsState.isPageLoading.value = true
            movieDetailsState.movieDetails.value = null
            movieDetailsState.movieDetails.value = landingPageRepository.getMovieDetails(movieId = movieId)
            movieDetailsState.movieCredits.value = landingPageRepository.getMovieCredits(movieId = movieId)
            movieDetailsState.isPageLoading.value = false
        }
    }

    fun clearMovieDetails() {
        movieDetailsState.movieDetails.value = null
        movieDetailsState.movieCredits.value = null
    }

    fun getPersonDetails(personId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            clearPersonDetails()
            personDetailsState.personDetails.value = landingPageRepository.getPersonDetails(personId)
        }
    }

    fun clearPersonDetails() {
        personDetailsState.personDetails.value = null
    }
}