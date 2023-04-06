package de.saringer.moviedemoapp.features.search

import androidx.compose.runtime.mutableStateOf
import de.saringer.moviedemoapp.features.search.searchbar.ChipState
import de.saringer.moviedemoapp.features.search.searchbar.SearchBarState

// TODO: move this to the SearchBarState and decide if this is necessary
data class SearchScreenState(
    val searchBarState: SearchBarState = SearchBarState(
        chips = listOf(
            ChipState(text = mutableStateOf("People")),
            ChipState(text = mutableStateOf("Movies")),
            ChipState(text = mutableStateOf("TV Shows")),
            ChipState(text = mutableStateOf("Companies")),
            ChipState(text = mutableStateOf("all")),
        )
    )
)