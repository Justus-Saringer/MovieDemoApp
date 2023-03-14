package de.saringer.moviedemoapp.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import de.saringer.moviedemoapp.graphsandnavigation.MainNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // TODO: add BottomBarState for visibility
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        bottomBar = { BottomBarNavigation(navController = navController) },
    ) {
        MainNavGraph(
            navController = navController,
            paddingValues = it
        )
    }
}