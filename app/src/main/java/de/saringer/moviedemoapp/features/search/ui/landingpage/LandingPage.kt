package de.saringer.moviedemoapp.features.search.ui.landingpage

import androidx.compose.runtime.Composable
import de.saringer.moviedemoapp.features.search.SearchViewModel

@Composable
fun LandingPage(viewModel: SearchViewModel, onMovieDetails: (Int) -> Unit) {
    PopularMoviesRow(viewModel = viewModel, onClick = onMovieDetails)
}