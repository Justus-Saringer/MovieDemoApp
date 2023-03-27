package de.saringer.moviedemoapp.features.search.ui.searchbar

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import de.saringer.moviedemoapp.features.search.ui.searchbar.ChipState

data class SearchBarState(
    val searchInput: MutableState<String> = mutableStateOf(""),
    val selectedChip: MutableState<String> = mutableStateOf(""),
    val chips: List<ChipState> = emptyList()
)