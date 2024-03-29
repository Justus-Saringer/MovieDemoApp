package de.saringer.moviedemoapp.features

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import de.saringer.moviedemoapp.graphsandnavigations.MainNavGraph

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val isBottomBarVisible = remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        bottomBar = {
            AnimatedVisibility(visible = isBottomBarVisible.value, enter = expandVertically(), exit = shrinkVertically()) {
                BottomBarNavigation(navController = navController)
            }
        },
    ) {
        MainNavGraph(
            navController = navController,
            paddingValues = it,
            isBottomBarVisible = isBottomBarVisible
        )
    }
}