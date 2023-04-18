package de.saringer.moviedemoapp.features.search.landingpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.features.search.datasources.network.domain.discover.Movie
import de.saringer.moviedemoapp.shared.composables.LinearLoadingIndicator
import de.saringer.moviedemoapp.shared.composables.MoviePreviewItem

@Composable
fun MoviesRow(viewModel: SearchViewModel, onClick: (Int) -> Unit, movies: LazyPagingItems<Movie>) {

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(168.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Loading")
                            Spacer(modifier = Modifier.size(8.dp))
                            LinearLoadingIndicator()
                        }
                    }
                }
            }
            else -> {}
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
            else -> {}
        }
    }
}
