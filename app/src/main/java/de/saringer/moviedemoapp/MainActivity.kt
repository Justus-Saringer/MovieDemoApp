package de.saringer.moviedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.saringer.moviedemoapp.features.LoginScreen
import de.saringer.moviedemoapp.features.LoginScreenState
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