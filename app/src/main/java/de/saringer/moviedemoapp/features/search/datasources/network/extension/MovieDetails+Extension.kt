package de.saringer.moviedemoapp.features.search.datasources.network.extension

import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.Genre
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.MovieDetails
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.ProductionCompany
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.ProductionCountry
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviedetails.SpokenLanguage
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails.GenreRemote
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails.MovieDetailsResponse
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails.ProductionCompanyRemote
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails.ProductionCountryRemote
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails.SpokenLanguageRemote

fun MovieDetailsResponse.toMovieDetails() = MovieDetails(
    adult = adult ?: true,
    backdropPath = backdropPath,
    belongsToCollection = belongsToCollection,
    budget = budget,
    genres = genres?.mapNotNull { GenreRemote -> GenreRemote?.toGenre() } ?: emptyList(),
    homepage = homepage,
    id = id ?: 0,
    imdbId = imdbId,
    originalLanguage = originalLanguage ?: "Original language not available",
    originalTitle = originalTitle,
    overview = overview ?: "No overview available",
    popularity = popularity,
    posterPath = posterPath,
    productionCompanies = productionCompanies?.mapNotNull { ProductionCompany -> ProductionCompany?.toProductionCompany() } ?: emptyList(),
    productionCountries = productionCountries?.mapNotNull { ProductionCountry -> ProductionCountry?.toProductionCountry() } ?: emptyList(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    spokenLanguages = spokenLanguages?.mapNotNull { spokenLanguage -> spokenLanguage?.toSpokenLanguage() } ?: emptyList(),
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

private fun GenreRemote.toGenre() = Genre(id = id, name = name)

private fun ProductionCompanyRemote.toProductionCompany() = ProductionCompany(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)

private fun ProductionCountryRemote.toProductionCountry() = ProductionCountry(iso31661 = iso31661, name = name)

private fun SpokenLanguageRemote.toSpokenLanguage() = SpokenLanguage(iso6391 = iso6391, name = name)