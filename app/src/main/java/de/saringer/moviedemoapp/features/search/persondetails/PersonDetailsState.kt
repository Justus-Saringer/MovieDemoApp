package de.saringer.moviedemoapp.features.search.persondetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.saringer.moviedemoapp.features.search.datasources.network.domain.persondetails.PersonDetails
import de.saringer.moviedemoapp.shared.enums.ScreenState

data class PersonDetailsState(
    val screenState: MutableState<ScreenState> = mutableStateOf(ScreenState.LOADING),
    val personDetails: MutableState<PersonDetails?> = mutableStateOf(null),
    val isExpanded: MutableState<Boolean> = mutableStateOf(false)
)