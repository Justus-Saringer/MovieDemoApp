package de.saringer.moviedemoapp.graphsandnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.saringer.moviedemoapp.features.SearchScreen
import de.saringer.moviedemoapp.features.SettingsScreen
import de.saringer.moviedemoapp.features.YoursScreen

@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomNavItem.Search.route) {

        composable(BottomNavItem.Settings.route) {
            SettingsScreen(paddingValues = paddingValues)
            // TODO: create SettingsScreenState
        }

        composable(BottomNavItem.Search.route) {
            SearchScreen(paddingValues = paddingValues)
            // TODO: create SearchScreenState
        }

        composable(BottomNavItem.Yours.route) {
            YoursScreen(paddingValues = paddingValues)
            // TODO: create YoursScreenState
        }
    }
}