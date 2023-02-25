package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.runtime.mutableStateOf

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