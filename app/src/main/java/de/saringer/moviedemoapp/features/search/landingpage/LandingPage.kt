package de.saringer.moviedemoapp.features.search.landingpage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import de.saringer.moviedemoapp.features.search.SearchViewModel

@Composable
fun LandingPage(viewModel: SearchViewModel, onMovieDetails: (Int) -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Most Popular")
        Spacer(modifier = Modifier.height(8.dp))
        MoviesRow(viewModel = viewModel, onClick = onMovieDetails, movies = viewModel.getMostPopularMovies().collectAsLazyPagingItems())
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "High Rated")
        Spacer(modifier = Modifier.height(8.dp))
        MoviesRow(viewModel = viewModel, onClick = onMovieDetails, movies = viewModel.getHighRatedMovies().collectAsLazyPagingItems())
    }
}