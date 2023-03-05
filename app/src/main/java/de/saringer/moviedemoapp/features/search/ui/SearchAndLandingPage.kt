package de.saringer.moviedemoapp.features.search.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.saringer.moviedemoapp.R
import de.saringer.moviedemoapp.features.search.SearchViewModel
import de.saringer.moviedemoapp.features.search.ui.landingpage.LandingPage
import de.saringer.moviedemoapp.shared.extensions.noRippleClickable
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.lightBlue

@Composable
fun SearchAndLandingPage(
    paddingValues: PaddingValues,
    state: SearchScreenState,
    onSearch: () -> Unit,
    viewModel: SearchViewModel,
    onMovieDetails: (Int) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .noRippleClickable { focusManager.clearFocus() }
            .padding(paddingValues)
    ) {
        SearchBar(state = state.searchBarState, onSearch = onSearch)

        Spacer(modifier = Modifier.size(16.dp))

        when {
            state.searchBarState.searchInput.value.isNotEmpty() -> {
                SearchResultsPage()
            }
            else -> LandingPage(viewModel = viewModel, onMovieDetails = onMovieDetails)
        }
    }
}

@Composable
private fun SearchBar(state: SearchBarState, onSearch: () -> Unit) {
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
fun SearchField(state: SearchBarState, onSearch: () -> Unit) {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChipFilter(state: SearchBarState) {
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

@Preview
@Composable
private fun SearchPreview() {
    MovieDemoAppTheme {
        val viewModel = hiltViewModel<SearchViewModel>()
        SearchAndLandingPage(
            paddingValues = PaddingValues(),
            state = SearchScreenState(),
            onSearch = {},
            onMovieDetails = {},
            viewModel = viewModel
        )
    }
}