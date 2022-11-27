package de.saringer.moviedemoapp.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.LineScaleProgressIndicator
import de.saringer.moviedemoapp.R
import de.saringer.moviedemoapp.ui.theme.MovieDemoAppTheme
import de.saringer.moviedemoapp.ui.theme.orange
import de.saringer.moviedemoapp.ui.theme.yellow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    state: LoginScreenState,
    onContinueClick: () -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .padding(60.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TitleArea()

            UserInputArea(state = state, keyboardController = keyboardController, onContinueClick = onContinueClick)

            Spacer(modifier = Modifier.size(16.dp))

            ButtonArea(state = state, keyboardController = keyboardController, onContinueClick = onContinueClick)
        }

        LoadingIndication(state.isLoading.value)
    }
}

@Composable
private fun LoadingIndication(isVisible: Boolean) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
                .background(Color.Black)
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            awaitPointerEvent(pass = PointerEventPass.Initial)
                                .changes
                                .forEach { it.consume() }
                        }
                    }
                }
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LineScaleProgressIndicator(
                color = yellow,
                animationDuration = 800,
                animationDelay = 200,
                startDelay = 0,
                lineWidth = 3.dp,
                lineCount = 5,
                minLineHeight = 3.dp,
                maxLineHeight = 30.dp
            )
        }
    }
}

@Composable
private fun TitleArea() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(id = R.string.movie_database_app),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Login",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun UserInputArea(state: LoginScreenState, keyboardController: SoftwareKeyboardController?, onContinueClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // username
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.usernameInput.value,
            singleLine = true,
            label = { Text(text = stringResource(id = R.string.username)) },
            onValueChange = { newValue ->
                state.usernameInput.value = newValue
            },
            enabled = !state.isLoading.value,
            trailingIcon = {
                if (state.usernameInput.value.isNotBlank()) {
                    IconButton(
                        onClick = {
                            state.usernameInput.value = ""
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
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

        Spacer(modifier = Modifier.size(16.dp))

        // password
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.passwordInput.value,
            maxLines = 1,
            label = { Text(text = stringResource(id = R.string.password)) },
            onValueChange = { newValue ->
                state.passwordInput.value = newValue
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    /* TODO: add check if is available */
                    keyboardController?.hide()
                }
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            enabled = !state.isLoading.value,
            trailingIcon = {
                IconButton(
                    onClick = { state.isPasswordVisible.value = !state.isPasswordVisible.value },
                ) {
                    Icon(
                        imageVector = if (state.isPasswordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation = if (state.isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
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
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ButtonArea(state: LoginScreenState, keyboardController: SoftwareKeyboardController?, onContinueClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            onClick = {
                keyboardController?.hide()
                onContinueClick()
                /*TODO integrate login request*/
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = orange)
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            elevation = null,
            onClick = {
                keyboardController?.hide()
                onContinueClick()
                /*TODO add logic for continuing without account */
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.LightGray
            )
        ) {
            Text(text = stringResource(id = R.string.skip))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    MovieDemoAppTheme {
        val state = LoginScreenState.rememberState()
        LoginScreen(state = state) {}
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenLoadingPreview() {
    MovieDemoAppTheme {
        val state = LoginScreenState.rememberState(isLoading = true)
        LoginScreen(state = state) {}
    }
}