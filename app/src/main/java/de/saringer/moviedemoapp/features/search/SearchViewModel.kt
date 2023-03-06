package de.saringer.moviedemoapp.features.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import de.saringer.moviedemoapp.features.search.datasources.network.LandingPageRepository
import de.saringer.moviedemoapp.features.search.datasources.network.domain.discover.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val landingPageRepository: LandingPageRepository
) : ViewModel() {

    fun getMostPopularMovies(): Flow<PagingData<Movie>> = landingPageRepository.getMostPopularMovies().cachedIn(viewModelScope)
}