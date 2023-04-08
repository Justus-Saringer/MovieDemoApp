package de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails

data class PersonDetails(
    val adult: Boolean,
    val alsoKnownAs: List<String?>? = null,
    val biography: String? = null,
    val birthday: String? = null,
    val deathday: String? = null,
    val gender: Int? = null,
    val homepage: String? = null,
    val id: Int,
    val imdbId: String? = null,
    val knownForDepartment: String? = null,
    val name: String? = null,
    val placeOfBirth: String? = null,
    val popularity: Double? = null,
    val profilePath: String? = null,
)