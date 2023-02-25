package de.saringer.moviedemoapp.features.search.datasources.network.domain

data class DiscoverModel(
    val page: Int? = null,
    val results: List<Movie> = emptyList(),
    val totalPages: Int? = null,
    val totalResults: Int? = null
)
