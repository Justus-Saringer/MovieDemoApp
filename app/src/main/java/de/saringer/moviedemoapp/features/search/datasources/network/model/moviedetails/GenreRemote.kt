package de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreRemote(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)