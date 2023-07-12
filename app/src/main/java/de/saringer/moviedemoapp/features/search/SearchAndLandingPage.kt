package de.saringer.moviedemoapp.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.saringer.moviedemoapp.features.search.landingpage.LandingPage
import de.saringer.moviedemoapp.features.search.searchbar.SearchBar
import de.saringer.moviedemoapp.shared.extensions.noRippleClickable
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme

@Composable
fun SearchAndLandingPage(
    paddingValues: PaddingValues,
    state: SearchScreenState,
    onSearch: () -> Unit,
    viewModel: SearchViewModel,
    onMovieDetails: (Int) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .noRippleClickable { focusManager.clearFocus() }
            .padding(paddingValues)
    ) {

        SearchBar(state = state.searchBarState, onSearch = onSearch)

        Spacer(modifier = Modifier.size(16.dp))

        when {
            state.searchBarState.searchInput.value.isNotEmpty() -> {
                SearchResultsPage()
            }
            else -> LandingPage(viewModel = viewModel, onMovieDetails = onMovieDetails)
        }
    }
}

@Preview
@Composable
private fun SearchPreview() {
    MovieDemoAppTheme {
        val viewModel = hiltViewModel<SearchViewModel>()
        SearchAndLandingPage(
            paddingValues = PaddingValues(),
            state = SearchScreenState(),
            onSearch = {},
            onMovieDetails = {},
            viewModel = viewModel
        )
    }
}