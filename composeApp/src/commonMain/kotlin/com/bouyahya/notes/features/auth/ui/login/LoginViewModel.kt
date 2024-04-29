package com.bouyahya.notes.features.auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bouyahya.notes.core.utils.Result
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    val state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState()),
) : ViewModel() {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UpdateLoginFields -> updateLoginFields(event)
            is LoginEvent.Submit -> submit()
        }
    }

    private fun updateLoginFields(event: LoginEvent.UpdateLoginFields) {
        state.value = state.value.copy(loginForm = event.loginForm)
    }

    private fun submit() {
        viewModelScope.launch {
            if (state.value.loginForm.email.isEmpty() ||
                state.value.loginForm.email.isEmpty()
            ) {
                validationEventChannel.send(
                    ValidationEvent.Failure(
                        message = "Email and password are required"
                    )
                )
                return@launch
            }

            state.update {
                it.copy(
                    isLoading = true
                )
            }

            when (val result = authRepository.login(
                state.value.loginForm.email,
                state.value.loginForm.password
            )) {
                is Result.Success -> {
                    validationEventChannel.send(ValidationEvent.Success)
                }

                is Result.Failure -> {
                    validationEventChannel.send(
                        ValidationEvent.Failure(
                            message = result.error
                        )
                    )
                }
            }
        }.invokeOnCompletion {
            state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }
}