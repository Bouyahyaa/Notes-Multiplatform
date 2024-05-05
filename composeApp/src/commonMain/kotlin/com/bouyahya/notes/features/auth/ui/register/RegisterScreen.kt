package com.bouyahya.notes.features.auth.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.bouyahya.notes.core.validation.ValidateEmptyField
import com.bouyahya.notes.core.validation.ValidationResult
import com.bouyahya.notes.features.auth.ui.register.components.SuccessDialog
import com.bouyahya.notes.features.auth.ui.validation.ValidateConfirmPassword
import com.bouyahya.notes.features.auth.ui.validation.ValidateEmail
import com.bouyahya.notes.features.auth.ui.validation.ValidatePassword
import com.bouyahya.notes.navigation.auth.AuthScreenRoute
import com.bouyahya.notes.uikit.CustomTextField
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var openSuccessRegisterDialog by remember { mutableStateOf(false) }

    var nameErrorMessage by remember { mutableStateOf<String?>(null) }
    var emailErrorMessage by remember { mutableStateOf<String?>(null) }
    var passwordErrorMessage by remember { mutableStateOf<String?>(null) }
    var confirmPasswordErrorMessage by remember { mutableStateOf<String?>(null) }
    var phoneErrorMessage by remember { mutableStateOf<String?>(null) }

    fun validateName(name: String = state.registerForm.email): ValidationResult =
        ValidateEmptyField.execute(name).also {
            nameErrorMessage = it.errorMessage
        }

    fun validateEmail(email: String = state.registerForm.email): ValidationResult =
        ValidateEmail.execute(email)
            .also {
                emailErrorMessage = it.errorMessage
            }


    fun validatePassword(password: String = state.registerForm.password): ValidationResult =
        ValidatePassword.execute(password)
            .also {
                passwordErrorMessage = it.errorMessage
            }

    fun validateConfirmPassword(confirmPassword: String = state.registerForm.confirmPassword): ValidationResult =
        ValidateConfirmPassword.execute(confirmPassword, state.registerForm.password)
            .also {
                confirmPasswordErrorMessage = it.errorMessage
            }

    fun validatePhone(phone: String = state.registerForm.phone): ValidationResult =
        ValidateEmptyField.execute(phone).also {
            phoneErrorMessage = it.errorMessage
        }

    fun validate(): Boolean {
        val validationFields =
            listOf(
                validateName(),
                validateEmail(),
                validatePassword(),
                validateConfirmPassword(),
                validatePhone()
            )
        return validationFields.none { !it.successful }
    }


    LaunchedEffect(Unit) {
        viewModel.validationEvents.collect { state ->
            when (state) {
                is ValidationEvent.Success -> openSuccessRegisterDialog = true

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
                .verticalScroll(rememberScrollState())
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
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }

            Image(
                painter = painterResource(Res.drawable.compose_multiplatform),
                contentDescription = "Logo",
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp)
            )

            CustomTextField(
                value = state.registerForm.name,
                label = "Name",
                onValueChange = {
                    validateName(it)
                    viewModel.onEvent(
                        RegisterEvent.UpdateRegisterForm(
                            state.registerForm.copy(
                                name = it
                            )
                        )
                    )
                },
                errorMessage = nameErrorMessage,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                value = state.registerForm.email,
                label = "Email",
                onValueChange = {
                    validateEmail(it)
                    viewModel.onEvent(
                        RegisterEvent.UpdateRegisterForm(
                            state.registerForm.copy(
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
                value = state.registerForm.password,
                label = "Password",
                onValueChange = {
                    validatePassword(it)
                    viewModel.onEvent(
                        RegisterEvent.UpdateRegisterForm(
                            state.registerForm.copy(
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

            CustomTextField(
                value = state.registerForm.confirmPassword,
                label = "Confirm Password",
                onValueChange = {
                    validateConfirmPassword(it)
                    viewModel.onEvent(
                        RegisterEvent.UpdateRegisterForm(
                            state.registerForm.copy(
                                confirmPassword = it
                            )
                        )
                    )
                },
                errorMessage = confirmPasswordErrorMessage,
                isVisible = false,
                keyboardType = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(5.dp))

            CustomTextField(
                value = state.registerForm.phone,
                label = "Phone",
                onValueChange = {
                    validatePhone(it)
                    viewModel.onEvent(
                        RegisterEvent.UpdateRegisterForm(
                            state.registerForm.copy(
                                phone = it
                            )
                        )
                    )
                },
                keyboardType = KeyboardType.Phone,
                errorMessage = phoneErrorMessage,
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
                        viewModel.onEvent(RegisterEvent.Submit)
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
                        text = "Register",
                        color = Color.White
                    )
            }
        }
    }

    if (openSuccessRegisterDialog)
        SuccessDialog(
            onNavigate = {
                openSuccessRegisterDialog = false
                navController.navigate(AuthScreenRoute.LoginScreen.route)
            },
            onDismissRequest = {
                openSuccessRegisterDialog = false
            }
        )
}