package de.saringer.moviedemoapp.features.search.network.extension

import de.saringer.moviedemoapp.features.search.network.domain.DiscoverModel
import de.saringer.moviedemoapp.features.search.network.domain.Movie
import de.saringer.moviedemoapp.features.search.network.model.DiscoverResponse
import de.saringer.moviedemoapp.features.search.network.model.MovieRemote

fun DiscoverResponse.toDiscoverModel() = DiscoverModel(
    page = page,
    totalPages = null,
    totalResults = null,
    results = results?.map { it?.toMovie() } ?: emptyList()
)

fun MovieRemote.toMovie() = Movie(
    adult = adult,
    backdropPath = backdropPath,
    genreIds = genreIds ?: emptyList(),
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video ?: false,
    voteAverage = voteAverage,
    voteCount = voteCount
)