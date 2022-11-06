package de.saringer.moviedemoapp.graphsandnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.saringer.moviedemoapp.features.LoginScreen
import de.saringer.moviedemoapp.features.LoginScreenState
import de.saringer.moviedemoapp.features.MainScreen

@Composable
fun RootNavGraph(navController: NavHostController) {

    // TODO: create a viewModel to store the states in
    val loginState = LoginScreenState.rememberState()

    NavHost(
        navController = navController,
        route = RootGraph.ROOT,
        startDestination = RootGraph.LOGIN
    ) {
        composable(route = RootGraph.LOGIN) {
            LoginScreen(state = loginState) {
                navController.navigate(RootGraph.BOTTOMBAR)
            }
        }

        composable(route = RootGraph.BOTTOMBAR) {
            MainScreen()
        }
    }
}

object RootGraph {
    const val LOGIN = "login"
    const val ROOT = "root"
    const val BOTTOMBAR = "bottom_bar_graph"
}