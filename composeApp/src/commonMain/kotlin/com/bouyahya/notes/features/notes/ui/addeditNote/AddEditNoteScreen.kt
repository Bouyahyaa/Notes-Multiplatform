package com.bouyahya.notes.features.notes.ui.addeditNote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bouyahya.notes.core.utils.ValidationEvent
import org.koin.core.parameter.parametersOf

class AddEditNoteScreen(
    val id: Long? = null
) : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<AddEditNoteViewModel>(
            parameters = {
                parametersOf(
                    "noteId" to id,
                )
            },
        )

        val state by viewModel.state.collectAsState()
        val note = state.note.value
        val scaffoldState: ScaffoldState = rememberScaffoldState()

        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(key) {
            viewModel.validationEvents.collect { state ->
                when (state) {
                    is ValidationEvent.Success -> navigator.pop()
                    is ValidationEvent.Failure -> scaffoldState.snackbarHostState.showSnackbar(
                        message = state.message,
                        actionLabel = "Dismiss"
                    )
                }
            }
        }

        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = Color.Transparent
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    ) {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                    }
            ) {
                // Return icon button
                IconButton(onClick = {
                    navigator.pop()
                }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Return"
                    )
                }

                // Title TextField
                OutlinedTextField(
                    value = note.title,
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditNoteEvent.UpdateNoteFields(
                                note.copy(
                                    title = it
                                )
                            )
                        )
                    },
                    label = { Text("Title") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                // Description TextField
                OutlinedTextField(
                    value = note.description,
                    onValueChange = {
                        viewModel.onEvent(
                            AddEditNoteEvent.UpdateNoteFields(
                                note.copy(
                                    description = it
                                )
                            )
                        )
                    },
                    label = { Text("Description") },
                    minLines = 10,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            focusManager.clearFocus()
                        }
                    )
                )

                // Submit Button
                Button(
                    onClick = {
                        if (!state.isLoading)
                            viewModel.onEvent(AddEditNoteEvent.Submit)
                    },
                    enabled = !state.isLoading,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondaryVariant
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = if (id != null)
                            "Edit Note" else
                            "Add note",
                        color = Color.White
                    )
                }
            }
        }
    }
}