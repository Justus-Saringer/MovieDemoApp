package de.saringer.moviedemoapp.features.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.LineScaleProgressIndicator
import de.saringer.moviedemoapp.shared.composables.LinearLoadingIndicator
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.yellow

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        Spacer(modifier = Modifier.height(8.dp))
        LinearLoadingIndicator()
    }
}

@Composable
private fun Title() {
    Text(
        text = "TMDB App",
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onBackground
    )
}

@Preview
@Composable
private fun Preview() {
    MovieDemoAppTheme {
        LoadingScreen()
    }
}