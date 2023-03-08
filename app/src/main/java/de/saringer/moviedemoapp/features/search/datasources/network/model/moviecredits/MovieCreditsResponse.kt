package de.saringer.moviedemoapp.features.search.datasources.network.model.moviecredits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsResponse(
    @SerialName("cast")
    val cast: List<CastRemote?>? = null,
    @SerialName("crew")
    val crew: List<CrewRemote?>? = null,
    @SerialName("id")
    val id: Int? = null
)