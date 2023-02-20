package de.saringer.moviedemoapp.shared.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.green
import de.saringer.moviedemoapp.ui.theme.orange
import de.saringer.moviedemoapp.ui.theme.yellow

@Composable
fun MoviePreviewItem(
    title: String,
    posterPath: String,
    releaseDate: String,
    popularity: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        val isImageLoading = remember { mutableStateOf(false) }

        Card() {
            ConstraintLayout(
                modifier = Modifier
                    .height(168.dp)
                    .width(124.dp)
            ) {
                val (image, popularityIndicator, loadingSpinner) = createRefs()

                SubcomposeAsyncImage(
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                    model = "https://image.tmdb.org/t/p/original$posterPath",
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    error = { rememberVectorPainter(image = Icons.Default.Movie) },
                    onLoading = { isImageLoading.value = true },
                    onSuccess = { isImageLoading.value = false }
                )

                this@Column.AnimatedVisibility(
                    modifier = Modifier.constrainAs(loadingSpinner) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                    visible = isImageLoading.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    LinearLoadingIndicator()
                }

                Popularity(
                    modifier = Modifier.constrainAs(popularityIndicator) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    },
                    popularity = popularity
                )
            }
        }

        // title
        Text(
            modifier = Modifier.width(124.dp),
            text = title,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        // release date
        Text(
            modifier = Modifier.width(124.dp),
            text = releaseDate,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.overline,
            maxLines = 1
        )
    }
}

@Composable
private fun Popularity(modifier: Modifier = Modifier, popularity: Int) {
    val circleIndicatorColor = when {
        popularity >= 70 -> green
        popularity in 70 downTo 45 -> yellow
        else -> orange
    }

    Box(modifier = modifier.padding(2.dp), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            progress = 1f,
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp)
                .background(Color(red = 255, blue = 255, green = 255, alpha = 125)),
            strokeWidth = 2.dp,
            color = MaterialTheme.colors.primary
        )

        CircularProgressIndicator(
            progress = (popularity / 100f),
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp,
            color = circleIndicatorColor
        )

        Text(text = popularity.toString(), style = MaterialTheme.typography.body2)
    }
}

// region previews
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun MoviePreviewItemPreview() {
    MovieDemoAppTheme {
        MoviePreviewItem(
            title = "catch me if you can",
            posterPath = "/sdYgEkKCDPWNU6KnoL4qd8xZ4w7.jpg",
            releaseDate = "23.04.2023",
            popularity = 80
        ) {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun MoviePreviewItemMiddleScorePreview() {
    MovieDemoAppTheme {
        MoviePreviewItem(
            title = "catch me if you can",
            posterPath = "/sdYgEkKCDPWNU6KnoL4qd8xZ4w7.jpg",
            releaseDate = "23.04.2023",
            popularity = 55
        ) {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun MoviePreviewItemLowScorePreview() {
    MovieDemoAppTheme {
        MoviePreviewItem(
            title = "catch me if you can",
            posterPath = "/sdYgEkKCDPWNU6KnoL4qd8xZ4w7.jpg",
            releaseDate = "23.04.2023",
            popularity = 32
        ) {}
    }
}
// endregion