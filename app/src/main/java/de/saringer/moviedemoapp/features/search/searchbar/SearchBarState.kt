package de.saringer.moviedemoapp.features.search.searchbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class SearchBarState(
    val searchInput: MutableState<String> = mutableStateOf(""),
    val selectedChip: MutableState<String> = mutableStateOf(""),
    val chips: List<ChipState> = emptyList(),
)