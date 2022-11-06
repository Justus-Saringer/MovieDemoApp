package de.saringer.moviedemoapp.features

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import de.saringer.moviedemoapp.graphsandnavigation.MainNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBarNavigation(navController = navController)}
    ) {
        MainNavGraph(navController = navController, paddingValues = PaddingValues())
    }
}