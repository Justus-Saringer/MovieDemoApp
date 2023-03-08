package de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits

data class MovieCredits(
    val cast: List<Cast?> = emptyList(),
    val crew: List<Crew?> = emptyList(),
    val id: Int? = null
)
