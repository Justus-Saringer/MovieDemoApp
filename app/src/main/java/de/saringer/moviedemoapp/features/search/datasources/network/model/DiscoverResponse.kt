package de.saringer.moviedemoapp.features.search.datasources.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverResponse(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<MovieRemote?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)