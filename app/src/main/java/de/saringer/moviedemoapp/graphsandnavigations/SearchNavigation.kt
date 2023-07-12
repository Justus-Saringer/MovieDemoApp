package de.saringer.moviedemoapp.graphsandnavigations

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
import de.saringer.moviedemoapp.features.search.SearchAndLandingPage
import de.saringer.moviedemoapp.features.search.SearchScreenState
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.features.search.moviedetails.MovieDetailsPage
import de.saringer.moviedemoapp.features.search.persondetails.PersonDetailsPage

@Composable
fun SearchNavigation(
    paddingValues: PaddingValues,
    isBottomBarVisible: MutableState<Boolean>
) {

    val viewModel = hiltViewModel<SearchViewModel>()
    val searchScreenNavController = rememberNavController()

    NavHost(
        navController = searchScreenNavController,
        startDestination = "searchAndLanding",
    ) {
        composable("searchAndLanding") {
            SearchAndLandingPage(
                paddingValues = paddingValues,
                state = viewModel.searchScreenState,
                onSearch = { viewModel.doSearch() },
                viewModel = viewModel,
                onMovieDetails = { movieId ->
                    searchScreenNavController.navigate("movie/$movieId") {
                        launchSingleTop = true
                    }
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
                    builder = { nullable = true }
                )
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toInt() ?: -1
            if (handleMovieIdError(
                    movieId,
                    searchScreenNavController,
                    isBottomBarVisible
                )
            ) return@composable

            viewModel.getMovieDetailsWithCredits(movieId)

            MovieDetailsPage(
                modifier = Modifier,
                movieId = movieId,
                movieDetailsState = viewModel.movieDetailsState,
                onActorClick = { personId ->
                    searchScreenNavController.navigate("person/$personId") {
                        launchSingleTop = true
                    }
                }
            )

            BackHandler {
                isBottomBarVisible.value = true
                searchScreenNavController.navigate("searchAndLanding")
                viewModel.clearMovieDetails()
            }
        }

        composable(
            route = "person/{personid}",
            arguments = listOf(
                navArgument(
                    name = "personId",
                    builder = { nullable = true }
                )
            )
        ) { navBackStackEntry ->
            val personId = navBackStackEntry.arguments?.getString("personid")?.toInt() ?: -1
            if (personId == -1) return@composable

            viewModel.getPersonDetails(personId)

            PersonDetailsPage(state = viewModel.personDetailsState)


/*            BackHandler {
                isBottomBarVisible.value = true
                searchScreenNavController.navigate("searchAndLanding")
                viewModel.clearMovieDetails()
            }*/
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