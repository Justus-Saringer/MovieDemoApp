package de.saringer.moviedemoapp.features.search.searchbar

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.saringer.moviedemoapp.ui.theme.lightBlue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipFilter(state: SearchBarState) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        state.chips.forEach { chipState ->
            chipState.isSelected.value = chipState.text.value == state.selectedChip.value

            Chip(
                onClick = {
                    if (state.selectedChip.value == chipState.text.value) {
                        state.selectedChip.value = ""
                        chipState.isSelected.value = false
                    } else {
                        state.selectedChip.value = chipState.text.value
                        chipState.isSelected.value = true
                    }
                },
                content = {
                    Text(text = chipState.text.value)
                },
                colors = if (chipState.isSelected.value) {
                    ChipDefaults.chipColors(
                        backgroundColor = lightBlue,
                        contentColor = MaterialTheme.colors.onSecondary
                    )
                } else {
                    ChipDefaults.chipColors(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onPrimary
                    )
                }
            )

            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}