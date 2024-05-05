package com.bouyahya.notes.features.notes.ui.addeditNote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.uikit.CustomTextField
import org.koin.compose.koinInject

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    noteId: Long,
    viewModel: AddEditNoteViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()
    val note = state.note
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.validationEvents.collect { state ->
            when (state) {
                is ValidationEvent.Success -> navController.popBackStack()
                is ValidationEvent.Failure -> scaffoldState.snackbarHostState.showSnackbar(
                    message = state.message,
                    actionLabel = "Dismiss"
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        if (noteId != -1L)
            viewModel.onEvent(AddEditNoteEvent.GetNote(noteId))
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
                navController.popBackStack()
            }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Return"
                )
            }

            // Title TextField
            CustomTextField(
                value = note.title,
                label = "Title",
                onValueChange = {
                    viewModel.onEvent(
                        AddEditNoteEvent.UpdateNoteFields(
                            note.copy(
                                title = it
                            )
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            )

            // Description TextField
            CustomTextField(
                value = note.description,
                label = "Description",
                onValueChange = {
                    viewModel.onEvent(
                        AddEditNoteEvent.UpdateNoteFields(
                            note.copy(
                                description = it
                            )
                        )
                    )
                },
                maxLine = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            )

            // Submit Button
            Button(
                onClick = {
                    if (!state.isLoading)
                        viewModel.onEvent(AddEditNoteEvent.Submit(noteId))
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