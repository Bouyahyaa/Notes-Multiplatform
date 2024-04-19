package com.bouyahya.notes.core.navigation.auth_navigation

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.bouyahya.notes.core.navigation.bottom_navigation.BottomNavigation
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.features.auth.ui.login.LoginScreen
import com.bouyahya.notes.features.auth.ui.login.LoginViewModel

object LoginScreenNav : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<LoginViewModel>()
        val state by viewModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow
        val scaffoldState = rememberScaffoldState()

        LaunchedEffect(key) {
            viewModel.validationEvents.collect { state ->
                when (state) {
                    is ValidationEvent.Success -> navigator.push(BottomNavigation)
                    is ValidationEvent.Failure -> scaffoldState.snackbarHostState.showSnackbar(
                        message = state.message,
                        actionLabel = "Dismiss"
                    )
                }
            }
        }

        LoginScreen(
            state = state,
            onEvent = { viewModel.onEvent(it) },
            scaffoldState = scaffoldState
        )
    }
}