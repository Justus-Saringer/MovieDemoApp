package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.saringer.moviedemoapp.features.search.SearchViewModel

@Composable
fun SearchScreen(paddingValues: PaddingValues, state: SearchScreenState) {
    val viewModel = hiltViewModel<SearchViewModel>()

    val searchScreenNavController = rememberNavController()
    NavHost(
        navController = searchScreenNavController,
        startDestination = "searchAndLanding",
    ) {
        composable("searchAndLanding") {
            SearchAndLandingPage(
                paddingValues = paddingValues,
                state = state,
                onSearch = {},
                viewModel = viewModel,
                onMovieDetails = { movieId ->
                    searchScreenNavController.navigate("movie/$movieId")
                }
            )
        }

        composable("movie/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
                ?: throw IllegalArgumentException("Invalid navigation argument on route \"movie/{movieId}\"")
            viewModel.getMovieDetails(movieId)
            MovieDetailsPage(movieId = movieId, movieDetailsState = viewModel.movieDetailsState)
        }
    }
}