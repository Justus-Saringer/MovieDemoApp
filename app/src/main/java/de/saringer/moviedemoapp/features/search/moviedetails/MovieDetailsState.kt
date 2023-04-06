package de.saringer.moviedemoapp.features.search.moviedetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits.MovieCredits
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.MovieDetails

data class MovieDetailsState(
    val movieDetails: MutableState<MovieDetails?> = mutableStateOf(null),
    val movieCredits: MutableState<MovieCredits?> = mutableStateOf(null),
    val refresh: (Int) -> Unit,
    val refreshing: MutableState<Boolean> = mutableStateOf(false),
    val isImageLoading: MutableState<Boolean> = mutableStateOf(false),
    val isPageLoading: MutableState<Boolean> = mutableStateOf(true)
)
