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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddEditNoteScreen(
    state: AddEditNoteState,
    onEvent: (AddEditNoteEvent) -> Unit,
    onNavigation: (AddEditNoteNavigation) -> Unit,
    scaffoldState: ScaffoldState
) {
    val note = state.note.value
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
                onNavigation(AddEditNoteNavigation.BackClick)
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
                    onEvent(
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
                    onEvent(
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
                        onEvent(AddEditNoteEvent.Submit)
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
                    text = if (note.id.toInt() != -1)
                        "Edit Note" else
                        "Add note",
                    color = Color.White
                )
            }
        }
    }
}