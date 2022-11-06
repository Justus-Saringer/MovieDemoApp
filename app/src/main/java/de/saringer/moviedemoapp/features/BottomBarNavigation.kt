package de.saringer.moviedemoapp.features

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.saringer.moviedemoapp.graphsandnavigation.BottomNavItem

@Composable
fun BottomBarNavigation(navController: NavHostController) {

    val items = listOf(
        BottomNavItem.Yours,
        BottomNavItem.Search,
        BottomNavItem.Settings,
    )

    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination == item.route,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title, style = MaterialTheme.typography.body2) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Blue,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {

                        navController.graph.startDestinationId.let { route ->
                            popUpTo(id = route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}