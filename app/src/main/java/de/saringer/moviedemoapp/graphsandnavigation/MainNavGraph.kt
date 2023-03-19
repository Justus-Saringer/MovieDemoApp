package de.saringer.moviedemoapp.graphsandnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.saringer.moviedemoapp.features.search.ui.SearchScreen
import de.saringer.moviedemoapp.features.search.ui.SearchScreenState
import de.saringer.moviedemoapp.features.settings.SettingsScreen
import de.saringer.moviedemoapp.features.yours.YoursScreen

@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues, isBottomBarVisible: MutableState<Boolean>) {
    NavHost(navController = navController, startDestination = BottomNavItem.Search.route) {

        composable(BottomNavItem.Settings.route) {
            SettingsScreen(paddingValues = paddingValues)
            // TODO: create SettingsScreenState
        }

        composable(BottomNavItem.Search.route) {
            SearchScreen(paddingValues = paddingValues, state = SearchScreenState(), isBottomBarVisible = isBottomBarVisible)
            // TODO: put SearchScreenState into a viewModel
        }

        composable(BottomNavItem.Yours.route) {
            YoursScreen(paddingValues = paddingValues)
            // TODO: create YoursScreenState
        }
    }
}