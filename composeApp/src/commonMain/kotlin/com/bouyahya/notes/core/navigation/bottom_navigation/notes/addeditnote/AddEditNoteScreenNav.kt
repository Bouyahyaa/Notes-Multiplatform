package com.bouyahya.notes.core.navigation.bottom_navigation.notes.addeditnote

import androidx.compose.material.*
import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.features.notes.ui.addeditNote.AddEditNoteNavigation
import com.bouyahya.notes.features.notes.ui.addeditNote.AddEditNoteScreen
import com.bouyahya.notes.features.notes.ui.addeditNote.AddEditNoteViewModel
import org.koin.core.parameter.parametersOf

class AddEditNoteScreenNav(val id: Long? = null) : Screen {
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
        val scaffoldState: ScaffoldState = rememberScaffoldState()
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

        AddEditNoteScreen(
            state = state,
            scaffoldState = scaffoldState,
            onEvent = {
                viewModel.onEvent(it)
            },
            onNavigation = {
                when (it) {
                    is AddEditNoteNavigation.BackClick -> {
                        navigator.pop()
                    }
                }
            },
        )
    }
}