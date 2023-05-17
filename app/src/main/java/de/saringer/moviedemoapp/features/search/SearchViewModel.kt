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
import de.saringer.moviedemoapp.shared.enums.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val landingPageRepository: LandingPageRepository
) : ViewModel() {

    val movieDetailsState = MovieDetailsState(
        refresh = { movieId ->
            getMovieDetailsWithCredits(movieId)
        }
    )

    val personDetailsState = PersonDetailsState()

    fun getMostPopularMovies(): Flow<PagingData<Movie>> = landingPageRepository.getMostPopularMovies().cachedIn(viewModelScope)

    fun getHighRatedMovies(): Flow<PagingData<Movie>> = landingPageRepository.getHighRatedMovies().cachedIn(viewModelScope)

    fun getBestMoviesOfTheYear(year: Int): Flow<PagingData<Movie>> =
        landingPageRepository.getBestMoviesOfTheYear(year).cachedIn(viewModelScope)

    fun getMovieDetailsWithCredits(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            clearMovieDetails()

            movieDetailsState.screenState.value = ScreenState.LOADING

            movieDetailsState.movieDetails.value = landingPageRepository.getMovieDetails(movieId = movieId)
            movieDetailsState.movieCredits.value = landingPageRepository.getMovieCredits(movieId = movieId)

            setMovieScreenState()
            movieDetailsState.refreshing.value = false
        }
    }

    fun getPersonDetails(personId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            clearPersonDetails()
            personDetailsState.personDetails.value = landingPageRepository.getPersonDetails(personId)
            setPersonScreenState()
        }
    }

    fun clearMovieDetails() {
        movieDetailsState.movieDetails.value = null
        movieDetailsState.movieCredits.value = null
    }

    fun clearPersonDetails() {
        personDetailsState.personDetails.value = null
    }

    private fun setMovieScreenState() {
        when {
            movieDetailsState.movieDetails.value == null -> {
                movieDetailsState.screenState.value = ScreenState.ERROR
            }

            movieDetailsState.movieDetails.value != null -> {
                movieDetailsState.screenState.value = ScreenState.SUCCESS
            }

            else -> {
                movieDetailsState.screenState.value = ScreenState.LOADING
            }
        }
    }

    private fun setPersonScreenState() {
        when {
            personDetailsState.personDetails.value == null -> {
                personDetailsState.screenState.value = ScreenState.ERROR
            }

            personDetailsState.personDetails.value != null -> {
                personDetailsState.screenState.value = ScreenState.SUCCESS
            }

            else -> {
                movieDetailsState.screenState.value = ScreenState.LOADING
            }
        }
    }
}
