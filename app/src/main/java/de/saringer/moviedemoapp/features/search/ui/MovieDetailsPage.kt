package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.saringer.moviedemoapp.R
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.Genre
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.MovieDetails
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.ProductionCompany
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.ProductionCountry
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.blue
import de.saringer.moviedemoapp.ui.theme.green
import de.saringer.moviedemoapp.ui.theme.grey
import de.saringer.moviedemoapp.ui.theme.lightBlue
import de.saringer.moviedemoapp.ui.theme.orange
import de.saringer.moviedemoapp.ui.theme.yellow
import kotlin.random.Random

// pull to refresh
// https://developer.android.com/reference/kotlin/androidx/compose/material/pullrefresh/package-summary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailsPage(movieId: Int, movieDetailsState: MovieDetailsState) {
    with(movieDetailsState) {
        val refreshState = rememberPullRefreshState(
            refreshing = refreshing.value,
            onRefresh = { refresh.invoke(movieId) }
        )

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .pullRefresh(refreshState)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(236.dp),
                    contentDescription = movieDetails.value?.originalTitle,
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original${movieDetails.value?.posterPath}")
                        .crossfade(true)
                        .placeholder(R.drawable.ic_launcher_background.toDrawable())
                        .build(),
                    contentScale = ContentScale.Crop,
                    onLoading = { isImageLoading.value = true },
                    onSuccess = { isImageLoading.value = false },
                )

                TitleWithYear(originalTitle = movieDetails.value?.originalTitle, releaseDate = movieDetails.value?.releaseDate)

                RuntimeAndAdult(isForAdults = movieDetails.value?.adult, runtime = movieDetails.value?.runtime)

                movieDetails.value?.genres?.let { genres -> GenreChips(genres = genres) }

                Overview(overview = movieDetails.value?.overview)
            }

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = refreshing.value,
                state = refreshState
            )
        }
    }
}

@Composable
private fun TitleWithYear(originalTitle: String?, releaseDate: String?) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = originalTitle ?: "Title",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(8.dp))

        releaseDate?.let { releaseDate ->
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(blue)
                    .padding(horizontal = 4.dp),
                text = releaseDate.substring(0, 4),
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
private fun RuntimeAndAdult(isForAdults: Boolean?, runtime: Int?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isForAdults == true) {
            Text(
                modifier = Modifier
                    .border(
                        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .padding(horizontal = 4.dp),
                text = "18",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.size(8.dp))
        }

        if (runtime != null) {
            Text(
                text = "$runtime min",
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
private fun GenreChips(genres: List<Genre?>) {

    val colorList = listOf(blue, green, yellow, lightBlue, orange)

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        genres.forEach { genre ->
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(colorList[Random.nextInt(colorList.size)])
                    .padding(horizontal = 4.dp),
                text = genre?.name ?: "Genre",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@Composable
private fun Overview(overview: String?) {
    Text(
        text = "Overview:",
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.padding(start = 24.dp)
    )
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(
            text = overview ?: "No overview available",
            color = MaterialTheme.colors.onBackground
        )
    }
}

// region previews
@Preview
@Composable
fun MoviePagePreview() {
    MovieDemoAppTheme {
        val state = MovieDetailsState(
            movieDetails = remember {
                mutableStateOf(
                    MovieDetails(
                        adult = true,
                        backdropPath = "/zWDMQX0sPaW2u0N2pJaYA8bVVaJ.jpg",
                        belongsToCollection = null,
                        budget = 20000000,
                        genres = listOf(Genre(27, "Horror"), Genre(9648, "Mystery"), Genre(53, "Thriller")),
                        homepage = "https://www.knockatthecabin.com",
                        id = 61842,
                        imdbId = "tt15679400",
                        originalLanguage = "en",
                        originalTitle = "Knock at the cabin",
                        overview = "While vacationing at a remote cabin, a young girl and her two fathers are taken hostage by four armed strangers who demand that the family make an unthinkable choice to avert the apocalypse. With limited access to the outside world, the family must decide what they believe before all is lost.",
                        popularity = 3975.228,
                        posterPath = "/dm06L9pxDOL9jNSK4Cb6y139rrG.jpg",
                        productionCompanies = listOf(
                            ProductionCompany(
                                12236,
                                "/uV6QBPdn3MjQzAFdgEel6od7geg.png",
                                "Universal Pictures",
                                "US"
                            )
                        ),
                        productionCountries = listOf(ProductionCountry("US", "United States of America")),
                        releaseDate = "2023-02-01",
                        revenue = 52000000,
                        runtime = 100,
                        spokenLanguages = listOf(),
                        status = "Released",
                        tagline = "Save your family or save humanity. Make the choice.",
                        title = "Knock at the Cabin",
                        video = false,
                        voteAverage = 6.479,
                        voteCount = 871
                    )
                )
            },
            refresh = {}
        )
        MovieDetailsPage(movieId = 631842, movieDetailsState = state)
    }
}
// endregion