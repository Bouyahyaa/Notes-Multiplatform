package com.bouyahya.notes.features.auth.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.core.validation.ValidationResult
import com.bouyahya.notes.features.auth.ui.validation.ValidateEmail
import com.bouyahya.notes.features.auth.ui.validation.ValidatePassword
import com.bouyahya.notes.navigation.Graph
import com.bouyahya.notes.navigation.auth.AuthScreenRoute
import com.bouyahya.notes.uikit.CustomTextField
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    var emailErrorMessage by remember { mutableStateOf<String?>(null) }
    var passwordErrorMessage by remember { mutableStateOf<String?>(null) }

    fun validateEmail(email: String = state.loginForm.email): ValidationResult =
        ValidateEmail.execute(email)
            .also {
                emailErrorMessage = it.errorMessage
            }


    fun validatePassword(password: String = state.loginForm.password): ValidationResult =
        ValidatePassword.execute(password)
            .also {
                passwordErrorMessage = it.errorMessage
            }


    fun validate(): Boolean {
        val validationFields =
            listOf(
                validateEmail(),
                validatePassword()
            )
        return validationFields.none { !it.successful }
    }

    LaunchedEffect(Unit) {
        viewModel.validationEvents.collect { state ->
            when (state) {
                is ValidationEvent.Success -> navController.navigate(Graph.HOME) {
                    popUpTo(Graph.AUTH) {
                        inclusive = true
                    }
                }

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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                ) {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
        ) {
            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
            )

            CustomTextField(
                value = state.loginForm.email,
                label = "Email",
                onValueChange = {
                    validateEmail(it)
                    viewModel.onEvent(
                        LoginEvent.UpdateLoginForm(
                            state.loginForm.copy(
                                email = it
                            )
                        )
                    )
                },
                errorMessage = emailErrorMessage,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                value = state.loginForm.password,
                label = "Password",
                onValueChange = {
                    validatePassword(it)
                    viewModel.onEvent(
                        LoginEvent.UpdateLoginForm(
                            state.loginForm.copy(
                                password = it
                            )
                        )
                    )
                },
                errorMessage = passwordErrorMessage,
                isVisible = false,
                keyboardType = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryVariant
                ),
                onClick = {
                    if (!state.isLoading &&
                        validate()
                    )
                        viewModel.onEvent(LoginEvent.Submit)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        color = Color.White,
                        modifier = Modifier.size(20.dp),
                    )
                } else
                    Text(
                        text = "Login",
                        color = Color.White
                    )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Don't have an account? Register",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .clickable {
                        navController.navigate(AuthScreenRoute.RegisterScreen.route)
                    }
                    .padding(5.dp)
            )
        }
    }
}