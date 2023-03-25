package de.saringer.moviedemoapp.features.search.ui.moviedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import de.saringer.moviedemoapp.R
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits.Cast
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.Genre
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.MovieDetails
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.ProductionCompany
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.ProductionCountry
import de.saringer.moviedemoapp.shared.composables.LinearLoadingIndicator
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.blue
import de.saringer.moviedemoapp.ui.theme.green
import de.saringer.moviedemoapp.ui.theme.grey
import de.saringer.moviedemoapp.ui.theme.lightBlue
import de.saringer.moviedemoapp.ui.theme.lightGrey
import de.saringer.moviedemoapp.ui.theme.orange
import de.saringer.moviedemoapp.ui.theme.yellow
import java.text.NumberFormat
import java.util.*
import kotlin.random.Random

// pull to refresh
// https://developer.android.com/reference/kotlin/androidx/compose/material/pullrefresh/package-summary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieDetailsPage(modifier: Modifier, movieId: Int, movieDetailsState: MovieDetailsState) {
    with(movieDetailsState) {
        val refreshState = rememberPullRefreshState(
            refreshing = refreshing.value,
            onRefresh = { refresh.invoke(movieId) }
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .pullRefresh(refreshState)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {

            when {
                movieDetails.value?.originalTitle != null && movieDetails.value?.overview != null -> {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(236.dp),
                            contentDescription = movieDetails.value?.originalTitle,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data("https://image.tmdb.org/t/p/original${movieDetails.value?.backdropPath}")
                                .crossfade(true)
                                .placeholder(R.drawable.ic_launcher_background.toDrawable())
                                .build(),
                            contentScale = ContentScale.Inside,
                            onLoading = { isImageLoading.value = true },
                            onSuccess = { isImageLoading.value = false },
                        )

                        TitleWithYear(originalTitle = movieDetails.value?.originalTitle, releaseDate = movieDetails.value?.releaseDate)

                        RuntimeAndAdult(isForAdults = movieDetails.value?.adult, runtime = movieDetails.value?.runtime)

                        movieDetails.value?.genres?.let { genres -> GenreChips(genres = genres) }

                        Overview(overview = movieDetails.value?.overview)

                        movieCredits.value?.let {
                            Spacer(modifier = Modifier.size(8.dp))
                            CastRow(actors = it.cast, onClick = {/*TODO: add navigation to actor*/ })
                            Spacer(modifier = Modifier.size(8.dp))
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        Facts(movieDetailsState = movieDetailsState)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                else -> {
                    Text(text = "Something went wrong\n¯\\_(ツ)_/¯", textAlign = TextAlign.Center, color = lightBlue)
                }
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
        style = MaterialTheme.typography.caption,
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

@Composable
private fun ColumnScope.CastRow(actors: List<Cast?>, onClick: ((Int) -> Unit)) {
    Text(
        text = "Cast:",
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 24.dp)
    )
    Spacer(modifier = Modifier.size(4.dp))

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        item { Spacer(modifier = Modifier.size(8.dp)) }
        actors.forEach { actor ->
            this.item {
                if (actor != null) {
                    Spacer(modifier = Modifier.size(8.dp))

                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { actor.id?.let { onClick.invoke(it) } }
                    ) {
                        val isImageLoading = remember { mutableStateOf(false) }

                        Card() {
                            ConstraintLayout(
                                modifier = Modifier
                                    .height(168.dp)
                                    .width(112.dp)
                            ) {
                                val (image, loadingSpinner) = createRefs()

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
                                        .data("https://image.tmdb.org/t/p/original${actor.profilePath}")
                                        .crossfade(true)
                                        .placeholder(R.drawable.ic_launcher_background.toDrawable())
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
                            }
                        }

                        // character name
                        Text(
                            modifier = Modifier.width(112.dp),
                            text = actor.character.orEmpty(),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.body2,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        // actor's name
                        Text(
                            modifier = Modifier.width(112.dp),
                            text = actor.name.orEmpty(),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.overline,
                            maxLines = 1
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
        item { Spacer(modifier = Modifier.size(8.dp)) }
    }
}

@Composable
private fun Facts(movieDetailsState: MovieDetailsState) {
    Text(
        text = "Facts:",
        style = MaterialTheme.typography.caption,
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

        // budget
        val budget = movieDetailsState.movieDetails.value?.budget
        if (budget != null) {
            val formattedBudget = NumberFormat.getCurrencyInstance(Locale.US).format(budget)

            Text(text = "Film budget:\n$formattedBudget", color = MaterialTheme.colors.onBackground)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // revenue
        val revenue = movieDetailsState.movieDetails.value?.revenue
        if (revenue != null) {
            val formattedBudget = NumberFormat.getCurrencyInstance(Locale.US).format(revenue)

            Text(text = "Film revenue:\n$formattedBudget", color = MaterialTheme.colors.onBackground)
            Spacer(modifier = Modifier.height(16.dp))
        }

        // production companies
        Text(text = "Companies:", color = MaterialTheme.colors.onBackground)
        Spacer(modifier = Modifier.height(8.dp))

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(lightGrey),
            mainAxisAlignment = FlowMainAxisAlignment.Center,
            crossAxisSpacing = 8.dp,
            mainAxisSpacing = 16.dp
        ) {
            movieDetailsState.movieDetails.value?.productionCompanies?.forEach { productionCompany ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        modifier = Modifier.size(64.dp),
                        model = "https://image.tmdb.org/t/p/original${productionCompany?.logoPath}",
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        modifier = Modifier.width(80.dp),
                        text = productionCompany?.name.orEmpty(),
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        movieDetailsState.movieDetails.value?.productionCompanies?.let {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // production countries
        Text(text = "Production countries:", color = MaterialTheme.colors.onBackground)
        Spacer(modifier = Modifier.height(8.dp))

        val countries = movieDetailsState.movieDetails.value?.productionCountries ?: emptyList()

        if (countries.isNotEmpty()) {
            countries.forEach { country ->
                country?.name?.let {
                    Text(text = it, color = MaterialTheme.colors.onBackground)
                }
            }
        }
    }

}

// region previews
@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
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
                            ),
                            ProductionCompany(
                                12236,
                                "/uV6QBPdn3MjQzAFdgEel6od7geg.png",
                                "Universal Pictures",
                                "US"
                            ),
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
        MovieDetailsPage(modifier = Modifier, movieId = 631842, movieDetailsState = state)
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
private fun FailurePreview() {
    MovieDemoAppTheme {
        MovieDetailsPage(
            modifier = Modifier,
            movieId = -1,
            movieDetailsState = MovieDetailsState(refresh = {}),
        )
    }
}
// endregion