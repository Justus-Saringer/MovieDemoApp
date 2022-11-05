package de.saringer.moviedemoapp.graphsandnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    object Settings : BottomNavItem(title = "Settings", Icons.Default.Settings, "settings")
    object Search : BottomNavItem(title = "Search", Icons.Default.Search, "search")
    object Yours : BottomNavItem(title = "Yours", Icons.Default.Favorite, "yours")
}
