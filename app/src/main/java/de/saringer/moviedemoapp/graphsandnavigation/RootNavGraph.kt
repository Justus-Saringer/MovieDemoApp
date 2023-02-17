package de.saringer.moviedemoapp.graphsandnavigation

import android.provider.DocumentsContract.Root
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.saringer.moviedemoapp.features.MainScreen
import de.saringer.moviedemoapp.features.login.LoginViewModel
import de.saringer.moviedemoapp.features.login.ui.LoadingScreen
import de.saringer.moviedemoapp.features.login.ui.LoginScreen

@Composable
fun RootNavGraph(navController: NavHostController) {

    val loginViewModel = hiltViewModel<LoginViewModel>()
//    val loginViewModel: LoginViewModel = viewModel()

    NavHost(
        navController = navController,
        route = RootGraph.ROOT,
        startDestination = RootGraph.LOADING
    ) {
        composable(route = RootGraph.LOGIN) {
            LoginScreen(
                state = loginViewModel.loginState,
                onSignInAsUserClick = {
                    loginViewModel.loginAsUser()
                    if (loginViewModel.sessionIdUser?.success == true) navController.navigate(RootGraph.BOTTOMBAR)
                },
                onSignInAsGuestClick = {
                    loginViewModel.loginAsGuest()
                    if (loginViewModel.sessionIdGuest?.success == true) navController.navigate(RootGraph.BOTTOMBAR)
                },
            )
        }

        composable(route = RootGraph.BOTTOMBAR) {
            MainScreen()
        }

        composable(route = RootGraph.LOADING) {
            LoadingScreen()
            LaunchedEffect(key1 = loginViewModel.sessionIdUser?.success) {
                when (loginViewModel.sessionIdUser?.success) {
                    true -> {
                        navController.popBackStack()
                        navController.navigate(RootGraph.BOTTOMBAR)
                    }
                    else -> {
                        navController.popBackStack()
                        navController.navigate(RootGraph.LOGIN)
                    }
                }
            }
        }
    }
}

object RootGraph {
    const val LOGIN = "login"
    const val ROOT = "root"
    const val BOTTOMBAR = "bottom_bar_graph"
    const val LOADING = "loading"
}