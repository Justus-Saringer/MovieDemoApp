package de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails

data class CollectionDetails(
    val backdropPath: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val overview: String? = null,
    val parts: List<Part?> = emptyList(),
    val posterPath: String? = null
)
