package de.saringer.moviedemoapp.features.search.persondetails

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.saringer.moviedemoapp.R
import de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails.PersonDetails
import de.saringer.moviedemoapp.shared.extensions.getDateInAnotherFormat
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.grey
import de.saringer.moviedemoapp.ui.theme.lightBlue

@Composable
fun PersonDetailsPage(state: PersonDetailsState) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(236.dp),
            contentDescription = state.personDetails.value?.name.orEmpty(),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original${state.personDetails.value?.profilePath}")
                .crossfade(true)
                .placeholder(R.drawable.baseline_movie_24.toDrawable())
                .build(),
            contentScale = ContentScale.Inside,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Name(text = state.personDetails.value?.name ?: "Name")
        Spacer(modifier = Modifier.height(16.dp))

        Biography(text = state.personDetails.value?.biography, state = state)
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(grey)
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Birthday(date = state.personDetails.value?.birthday)
            Spacer(modifier = Modifier.height(8.dp))

            Deathday(date = state.personDetails.value?.deathday)
            Spacer(modifier = Modifier.height(8.dp))

            PlaceOfBirth(text = state.personDetails.value?.placeOfBirth.orEmpty())

            state.personDetails.value?.knownForDepartment?.let {
                Spacer(modifier = Modifier.height(8.dp))
                KnownDepartment(department = it)
            }
        }

    }
}

@Composable
private fun Name(text: String) {
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = text,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun Biography(text: String?, state: PersonDetailsState) {
    Text(
        text = "Biography:",
        style = MaterialTheme.typography.caption,
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier.padding(start = 24.dp)
    )

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(grey)
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .animateContentSize(
                animationSpec = tween(200)
            )
    ) {

        Text(
            text = text ?: "No overview available",
            color = MaterialTheme.colors.onBackground,
            maxLines = if (state.isExpanded.value) Int.MAX_VALUE else 5,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier
                .align(Alignment.End)
                .clip(RoundedCornerShape(4.dp))
                .clickable { state.isExpanded.value = !state.isExpanded.value },
            text = if (state.isExpanded.value) "collapse" else "expand",
            color = lightBlue,
        )
    }
}

@Composable
private fun Birthday(date: String?) {

    if (date.isNullOrEmpty()) return

    val formattedDate = date.getDateInAnotherFormat("yyyy-MM-dd", "dd.MM.YYYY")

    Text(
        text = "🎂 $formattedDate",
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun Deathday(date: String?) {

    val formattedDate = if (date.isNullOrEmpty()) "Not dead yet" else date.getDateInAnotherFormat("yyyy-MM-dd", "dd.MM.YYYY")

    Text(
        text = "🪦 $formattedDate",
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun PlaceOfBirth(text: String) {
    Text(
        text = "📍 Place of birth: $text",
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
private fun KnownDepartment(department: String) {
    Text(text = "🏛️ Deparment: $department", color = MaterialTheme.colors.onBackground)
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
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
                            biography = "Christopher Michael Pratt (born 21 June 1979) is an American actor, known for starring in both television and action films. He rose to prominence for his television roles, particularly in the NBC sitcom Parks and Recreation (2009–2015), for which he received critical acclaim and was nominated for the Critics' Choice Television Award for Best Supporting Actor in a Comedy Series in 2013. He also starred earlier in his career as Bright Abbott in The WB drama series Everwood (2002–2006) and had roles in Wanted (2008), Jennifer's Body (2009), Moneyball (2011), The Five-Year Engagement (2012), Zero Dark Thirty (2013), Delivery Man (2013), and Her (2013).\\n\\nPratt achieved leading man status in 2014, starring in two critically and commercially successful films: The Lego Movie as Emmet Brickowski, and Marvel Studios' Guardians of the Galaxy as Star-Lord. He starred in Jurassic World (2015) and Jurassic World: Fallen Kingdom (2018), and he reprised his Marvel role in Guardians of the Galaxy Vol. 2 (2017), Avengers: Infinity War (2018), Avengers: Endgame (2019), and the planned Guardians of the Galaxy Vol. 3. Meanwhile, in 2016 he was part of an ensemble cast in The Magnificent Seven and the male lead in Passengers.\\n\\nDescription above is from the Wikipedia article Chris Pratt, licensed under CC-BY-SA, full list of contributors on Wikipedia.",
                            birthday = "1979-06-21",
                            deathday = "1979-06-21",
                            gender = null,
                            homepage = null,
                            id = 73457,
                            imdbId = "nm0695435",
                            knownForDepartment = "Acting",
                            name = "Chris Pratt",
                            placeOfBirth = "Virginia, Minnesota, USA",
                            popularity = 59.422,
                            profilePath = "/83o3koL82jt30EJ0rz4Bnzrt2dd.jpg"
                        )
                    )
                }
            )
        )
    }
}