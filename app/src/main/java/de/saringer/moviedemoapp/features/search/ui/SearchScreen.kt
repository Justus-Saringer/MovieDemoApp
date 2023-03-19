package de.saringer.moviedemoapp.features.search.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.features.search.ui.moviedetails.MovieDetailsPage

@Composable
fun SearchScreen(paddingValues: PaddingValues, state: SearchScreenState, isBottomBarVisible: MutableState<Boolean>) {

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
                    isBottomBarVisible.value = false
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
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: -1
            if (handleMovieIdError(movieId, searchScreenNavController, isBottomBarVisible)) return@composable

            viewModel.getMovieDetailsWithCredits(movieId)
            MovieDetailsPage(modifier = Modifier, movieId = movieId, movieDetailsState = viewModel.movieDetailsState)

            BackHandler() {
                isBottomBarVisible.value = true
                searchScreenNavController.navigate("searchAndLanding")

            }
        }
    }
}

@Composable
private fun handleMovieIdError(
    movieId: Int,
    searchScreenNavController: NavHostController,
    isBottomBarVisible: MutableState<Boolean>
): Boolean {
    if (movieId == -1) {
        searchScreenNavController.navigate("searchAndLanding")
        isBottomBarVisible.value = true
        return true
    }
    return false
}