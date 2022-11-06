package de.saringer.moviedemoapp.graphsandnavigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = BottomNavItem.Search.route) {

        composable(BottomNavItem.Settings.route) {
            // TODO: create SettingsScreen() and state
        }

        composable(BottomNavItem.Search.route) {
            // TODO: create SearchScreen and state
        }

        composable(BottomNavItem.Yours.route) {
            // TODO: create YoursScreen and state
        }
    }
}