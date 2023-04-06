package de.saringer.moviedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.saringer.moviedemoapp.features.login.network.LoginRepository
import de.saringer.moviedemoapp.graphsandnavigations.RootNavGraph
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDemoAppTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }

    override fun onDestroy() {
        lifecycleScope.launch {
            loginRepository.deleteSession()
        }
        super.onDestroy()
    }

}