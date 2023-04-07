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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.saringer.moviedemoapp.R
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.green
import de.saringer.moviedemoapp.ui.theme.orange
import de.saringer.moviedemoapp.ui.theme.white
import de.saringer.moviedemoapp.ui.theme.yellow
import kotlin.math.roundToInt

@Composable
fun MoviePreviewItem(
    title: String,
    posterPath: String,
    releaseDate: String,
    voteAverage: Double,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onClick()
            }
    ) {
        val isImageLoading = remember { mutableStateOf(false) }

        Card() {
            ConstraintLayout(
                modifier = Modifier
                    .height(168.dp)
                    .width(112.dp)
            ) {
                val (image, popularityIndicator, loadingSpinner) = createRefs()

                AsyncImage(
                    modifier = Modifier.constrainAs(image) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    },
                    contentDescription = null,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original$posterPath")
                        .crossfade(true)
                        .placeholder(R.drawable.baseline_movie_24)
                        .build(),
                    contentScale = ContentScale.Crop,
                    onLoading = { isImageLoading.value = true },
                    onSuccess = { isImageLoading.value = false },
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
                    popularity = voteAverage
                )
            }
        }

        // title
        Text(
            modifier = Modifier.width(112.dp),
            text = title,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        // release date
        Text(
            modifier = Modifier.width(112.dp),
            text = releaseDate,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.overline,
            maxLines = 1
        )
    }
}

@Composable
private fun Popularity(modifier: Modifier = Modifier, popularity: Double) {
    val circleIndicatorColor = when {
        popularity.roundToInt() >= 7 -> green
        popularity.roundToInt() in 7 downTo 4 -> yellow
        else -> orange
    }

    Box(
        modifier = modifier
            .padding(2.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.background)
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = 1f,
            modifier = Modifier.size(26.dp),
            strokeWidth = 2.dp,
            color = MaterialTheme.colors.primary
        )

        CircularProgressIndicator(
            progress = (popularity / 10).toFloat(),
            modifier = Modifier.size(26.dp),
            strokeWidth = 2.dp,
            color = circleIndicatorColor
        )

        Text(
            text = popularity.toString(),
            style = MaterialTheme.typography.body1.copy(fontSize = 12.sp),
            color = white
        )
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
            voteAverage = 8.0
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
            voteAverage = 5.5
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
            voteAverage = 3.2
        ) {}
    }
}
// endregion