package de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails


data class MovieDetails(
    val adult: Boolean,
    val backdropPath: String? = null,
    val belongsToCollection: Boolean? = null,
    val budget: Int? = null,
    val genres: List<Genre?> = emptyList(),
    val homepage: String? = null,
    val id: Int,
    val imdbId: String? = null,
    val originalLanguage: String,
    val originalTitle: String? = null,
    val overview: String,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val productionCompanies: List<ProductionCompany?> = emptyList(),
    val productionCountries: List<ProductionCompany?> = emptyList(),
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spokenLanguages: List<SpokenLanguage?> = emptyList(),
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
