package de.saringer.moviedemoapp.features.search.datasources.network.extension

import de.saringer.moviedemoapp.features.search.datasources.network.domain.DiscoverModel
import de.saringer.moviedemoapp.features.search.datasources.network.domain.Movie
import de.saringer.moviedemoapp.features.search.datasources.network.model.DiscoverResponse
import de.saringer.moviedemoapp.features.search.datasources.network.model.MovieRemote
import kotlin.random.Random

fun DiscoverResponse.toDiscoverModel() = results?.mapNotNull { movieRemote -> movieRemote?.toMovie() }?.let {
    DiscoverModel(
        page = page,
        totalPages = null,
        totalResults = null,
        results = it
    )
}

fun MovieRemote.toMovie() = Movie(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds ?: emptyList(),
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity ?: 0.0,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title ?: "Title",
    video = video ?: false,
    voteAverage = voteAverage,
    voteCount = voteCount,
    id = id ?: Random.nextInt(999999)
)