package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme

@Composable
fun SearchResultsPage(paddingValues: PaddingValues) {

}

@Preview
@Composable
private fun SearchResultPagePreview() {
    MovieDemoAppTheme {
        SearchResultsPage(paddingValues = PaddingValues())
    }
}