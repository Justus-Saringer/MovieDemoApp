package de.saringer.moviedemoapp.features.search.persondetails

import androidx.compose.runtime.MutableState
import de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails.PersonDetails

data class PersonDetailsState(
    val personDetails: MutableState<PersonDetails>
)