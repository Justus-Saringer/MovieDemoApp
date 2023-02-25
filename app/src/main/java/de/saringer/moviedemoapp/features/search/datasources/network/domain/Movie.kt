package de.saringer.moviedemoapp.features.search.datasources.network.domain

data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genreIds: List<Int?> = emptyList(),
    val id: Int,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Double,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String,
    val video: Boolean,
    val voteAverage: Double? = null,
    val voteCount: Int? = null,
)
