package de.saringer.moviedemoapp.features.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ChipState(
    val text: MutableState<String> = mutableStateOf(""),
    val isSelected: MutableState<Boolean> = mutableStateOf(false)
)