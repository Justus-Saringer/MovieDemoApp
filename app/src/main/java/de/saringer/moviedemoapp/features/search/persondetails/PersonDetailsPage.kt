package de.saringer.moviedemoapp.features.search.persondetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails.PersonDetails
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.grey

@Composable
fun PersonDetailsPage(state: PersonDetailsState) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(236.dp),
            contentDescription = state.personDetails.value.name,
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original${state.personDetails.value.profilePath}")
                .crossfade(true)
                .placeholder(R.drawable.baseline_movie_24.toDrawable())
                .build(),
            contentScale = ContentScale.Inside,
//            onLoading = { isImageLoading.value = true },
//            onSuccess = { isImageLoading.value = false },
        )

        Title(text = state.personDetails.value.name ?: "Name")
        Spacer(modifier = Modifier.height(16.dp))

        Biography(text = state.personDetails.value.biography)
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 4.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(grey)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Birthday(date = state.personDetails.value.birthday ?: "No date available")
            Spacer(modifier = Modifier.height(8.dp))

            Deathday(date = state.personDetails.value.deathday ?: "Not dead yet")

            PlaceOfBirth(text = state.personDetails.value.placeOfBirth.orEmpty())
        }

    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun Biography(text: String?) {
    Text(
        text = "Biography:",
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
            text = text ?: "No overview available",
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun Birthday(date: String) {
    Text(
        text = "🎂 $date",
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun Deathday(date: String) {
    Text(
        text = "🪦 $date",
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun PlaceOfBirth(text: String) {
    Text(
        text = "Place of birth: $text",
        color = MaterialTheme.colors.onBackground
    )
}

@Preview
@Composable
private fun Preview() {
    MovieDemoAppTheme {
        PersonDetailsPage(
            state = PersonDetailsState(
                personDetails = remember {
                    mutableStateOf(
                        PersonDetails(
                            adult = false,
                            alsoKnownAs = listOf(),
                            biography = null,
                            birthday = null,
                            deathday = null,
                            gender = null,
                            homepage = null,
                            id = 0,
                            imdbId = null,
                            knownForDepartment = null,
                            name = null,
                            placeOfBirth = null,
                            popularity = null,
                            profilePath = null
                        )
                    )
                }
            )
        )
    }
}