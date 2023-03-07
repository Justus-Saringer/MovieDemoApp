package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.MovieDetails

data class MovieDetailsState(
    val movieDetails: MutableState<MovieDetails?> = mutableStateOf(null),
    val refreshing: MutableState<Boolean> = mutableStateOf(false),
    val refresh: (Int) -> Unit
)
