package de.saringer.moviedemoapp.features.search.ui.searchbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.saringer.moviedemoapp.R

@Composable
fun SearchBar(state: SearchBarState, onSearch: () -> Unit) {
    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
    ) {
        SearchField(state = state, onSearch = onSearch)

        Spacer(modifier = Modifier.size(4.dp))

        // TODO: implement onSelect Chip
        ChipFilter(state = state)

        Spacer(modifier = Modifier.size(4.dp))
        Divider(color = MaterialTheme.colors.onBackground)
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
private fun SearchField(state: SearchBarState, onSearch: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.searchInput.value,
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.search_bar_label)) },
        onValueChange = { newValue ->
            state.searchInput.value = newValue
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                onSearch()
            }
        ),
        trailingIcon = {
            AnimatedVisibility(
                visible = state.searchInput.value.isNotBlank(),
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                IconButton(
                    onClick = {
                        state.searchInput.value = ""
                    }
                ) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                }
            }

            AnimatedVisibility(
                visible = state.searchInput.value.isBlank(),
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.onPrimary,
            placeholderColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onPrimary,
            trailingIconColor = MaterialTheme.colors.onPrimary,
            unfocusedBorderColor = MaterialTheme.colors.onPrimary,
            focusedBorderColor = MaterialTheme.colors.onPrimary,
            unfocusedLabelColor = MaterialTheme.colors.onPrimary,
            focusedLabelColor = MaterialTheme.colors.onPrimary,
        )
    )
}
