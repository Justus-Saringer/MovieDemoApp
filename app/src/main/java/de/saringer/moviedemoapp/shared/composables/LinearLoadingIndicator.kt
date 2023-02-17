package de.saringer.moviedemoapp.shared.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.LineScaleProgressIndicator
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.yellow

@Composable
fun LinearLoadingIndicator() {
    LineScaleProgressIndicator(
        color = yellow,
        animationDuration = 800,
        animationDelay = 200,
        startDelay = 0,
        lineWidth = 3.dp,
        lineCount = 5,
        minLineHeight = 3.dp,
        maxLineHeight = 30.dp
    )
}

@Preview
@Composable
private fun Preview() {
    MovieDemoAppTheme {
        LinearLoadingIndicator()
    }
}