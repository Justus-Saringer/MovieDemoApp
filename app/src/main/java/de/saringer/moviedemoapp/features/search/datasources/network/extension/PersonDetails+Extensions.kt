package de.saringer.moviedemoapp.features.search.datasources.network.extension

import de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails.PersonDetails
import de.saringer.moviedemoapp.features.search.datasources.network.model.persondetails.PersonDetailsRemote

fun PersonDetailsRemote.toPersonDetails() = PersonDetails(
    adult = adult ?: false,
    alsoKnownAs = alsoKnownAs,
    biography = biography,
    birthday = birthday,
    deathday = deathday,
    gender = gender,
    homepage = homepage,
    id = id ?: -1,
    imdbId = imdbId,
    knownForDepartment = knownForDepartment,
    name = name,
    placeOfBirth = placeOfBirth,
    popularity = popularity,
    profilePath = profilePath,
)