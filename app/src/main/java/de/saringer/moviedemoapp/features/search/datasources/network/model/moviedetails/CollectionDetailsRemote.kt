package de.saringer.moviedemoapp.features.search.datasources.network.model.moviedetails

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDetailsRemote(
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("parts")
    val parts: List<PartRemote?>? = null,
    @SerialName("poster_path")
    val posterPath: String? = null
)