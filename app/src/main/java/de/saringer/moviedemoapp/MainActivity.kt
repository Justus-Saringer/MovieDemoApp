package de.saringer.moviedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.saringer.moviedemoapp.graphsandnavigation.RootNavGraph
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDemoAppTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}