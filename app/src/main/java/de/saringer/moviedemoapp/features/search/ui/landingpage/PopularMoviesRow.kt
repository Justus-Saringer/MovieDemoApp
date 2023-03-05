package de.saringer.moviedemoapp.features.search.ui.landingpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.shared.composables.LinearLoadingIndicator
import de.saringer.moviedemoapp.shared.composables.MoviePreviewItem

@Composable
fun PopularMoviesRow(viewModel: SearchViewModel, onClick: (Int) -> Unit) {
    val movies = viewModel.getMostPopularMovies().collectAsLazyPagingItems()

    LazyRow {
        items(
            items = movies,
            key = { it.id }
        ) { movie ->
            movie?.let {
                Spacer(modifier = Modifier.size(8.dp))
                MoviePreviewItem(
                    voteAverage = movie.voteAverage,
                    title = movie.title,
                    posterPath = movie.posterPath.orEmpty(),
                    releaseDate = movie.releaseDate.orEmpty(),
                    onClick = { onClick.invoke(movie.id) },
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }

        // First Load
        when (val state = movies.loadState.refresh) {
            is LoadState.Error -> {
                item { Text(text = "An Error occurred\n${state.error}", textAlign = TextAlign.Center) }
            }

            is LoadState.Loading -> {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Loading")
                        Spacer(modifier = Modifier.size(8.dp))
                        LinearLoadingIndicator()
                    }
                }
            }
            else -> {
                item { Text(text = "Something went Wrong") }
            }
        }

        when (val state = movies.loadState.append) { // Pagination
            is LoadState.Error -> {
                item { Text(text = "An Error occurred\n${state.error}", textAlign = TextAlign.Center) }
            }
            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")
                        Spacer(modifier = Modifier.size(8.dp))
                        LinearLoadingIndicator()
                    }
                }
            }
            else -> {
                item { Text(text = "Something went Wrong") }
            }
        }
    }
}
