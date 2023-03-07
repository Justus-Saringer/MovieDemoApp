package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme

@Composable
fun MovieDetailsPage(movieId: Int, movieDetailsState: MovieDetailsState) {

    // TODO: Swipe to refresh
    // TODO: add displaying the information here
    // TODO: maybe some other cool stuff like bottom sheets or so
}

@Preview
@Composable
fun MoviePagePreview() {
    MovieDemoAppTheme {
        val viewModel = hiltViewModel<SearchViewModel>()
        MovieDetailsPage(movieId = 631842, movieDetailsState = viewModel.movieDetailsState)
    }
}