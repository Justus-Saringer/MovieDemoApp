package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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


        // https://stackoverflow.com/questions/73127002/missing-road-arguments-value-while-using-navigation-in-jetpack-compose
        composable(
            route = "movie/{movieId}",
            arguments = listOf(
                navArgument(
                    name = "movieId",
                    builder = { nullable = true })
            )
        ) { backStackEntry ->
            // val movieId = backStackEntry.arguments?.getInt("movieId") ?: throw IllegalArgumentException("Invalid navigation argument on route \"movie/{movieId}\"")
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: -1
            if (movieId == -1) searchScreenNavController.navigate("searchAndLanding")

            viewModel.getMovieDetailsWithCredits(movieId)
            MovieDetailsPage(movieId = movieId, movieDetailsState = viewModel.movieDetailsState)
        }
    }
}