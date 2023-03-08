package de.saringer.moviedemoapp.features.search.datasources.network.extension

import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits.Cast
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits.Crew
import de.saringer.moviedemoapp.features.search.datasources.network.domain.moviecredits.MovieCredits
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviecredits.CastRemote
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviecredits.CrewRemote
import de.saringer.moviedemoapp.features.search.datasources.network.model.moviecredits.MovieCreditsResponse

fun MovieCreditsResponse.toMovieCredits() = MovieCredits(
    cast = cast?.mapNotNull { actor -> actor?.toCast() } ?: emptyList(),
    crew = crew?.mapNotNull { crewMember -> crewMember?.toCrew() } ?: emptyList(),
    id = id
)

private fun CastRemote.toCast() = Cast(
    adult = adult,
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    id = id,
    knownForDepartment = knownForDepartment,
    name = name,
    order = order,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,
)

private fun CrewRemote.toCrew() = Crew(
    adult = adult,
    creditId = creditId,
    department = department,
    gender = gender,
    id = id,
    job = job,
    knownForDepartment = knownForDepartment,
    name = name,
    originalName = originalName,
    popularity = popularity,
    profilePath = profilePath,

    )