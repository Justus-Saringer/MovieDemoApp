package de.saringer.moviedemoapp.graphsandnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.saringer.moviedemoapp.features.BottomBarScreen
import de.saringer.moviedemoapp.features.LoginScreen
import de.saringer.moviedemoapp.features.LoginScreenState

@Composable
fun RootNavGraph(navController: NavHostController) {

    // TODO: create a viewModel to store the states
    val loginState = LoginScreenState.rememberState()

    NavHost(
        navController = navController,
        startDestination = RootGraph.LOGIN
    ) {
        composable(route = RootGraph.LOGIN) {
            LoginScreen(state = loginState) {
                navController.navigate(RootGraph.BOTTOMBAR)
            }
        }

        composable(route = RootGraph.BOTTOMBAR) {
            BottomBarScreen()
        }
    }
}

object RootGraph {
    const val LOGIN = "login"
    const val BOTTOMBAR = "bottom_bar_graph"
}