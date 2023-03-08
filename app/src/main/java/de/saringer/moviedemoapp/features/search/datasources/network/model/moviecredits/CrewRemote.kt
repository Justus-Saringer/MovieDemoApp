package de.saringer.moviedemoapp.features.search.datasources.network.model.moviecredits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewRemote(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("credit_id")
    val creditId: String? = null,
    @SerialName("department")
    val department: String? = null,
    @SerialName("gender")
    val gender: Int? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("job")
    val job: String? = null,
    @SerialName("known_for_department")
    val knownForDepartment: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("profile_path")
    val profilePath: String? = null
)