package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme

// pull to refresh
// https://developer.android.com/reference/kotlin/androidx/compose/material/pullrefresh/package-summary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailsPage(movieId: Int, movieDetailsState: MovieDetailsState) {

    // TODO: add displaying the information here
    // TODO: maybe some other cool stuff like bottom sheets or so
    val refreshState = rememberPullRefreshState(
        refreshing = movieDetailsState.refreshing.value,
        onRefresh = { movieDetailsState.refresh.invoke(movieId) }
    )

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
        ) {

        }

        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = movieDetailsState.refreshing.value,
            state = refreshState
        )
    }
}

@Preview
@Composable
fun MoviePagePreview() {
    MovieDemoAppTheme {
        val viewModel = hiltViewModel<SearchViewModel>()
        MovieDetailsPage(movieId = 631842, movieDetailsState = viewModel.movieDetailsState)
    }
}