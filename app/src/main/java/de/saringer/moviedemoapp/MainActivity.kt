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
import de.saringer.moviedemoapp.features.LoginScreen
import de.saringer.moviedemoapp.features.LoginScreenState
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDemoAppTheme {
                val state = LoginScreenState.rememberState()
                LoginScreen(state = state)
            }
        }
    }
}