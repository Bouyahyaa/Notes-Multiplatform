package com.bouyahya.notes.features.auth.ui.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
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
) : ScreenModel {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UpdateLoginFields -> updateLoginFields(event)
            is LoginEvent.Submit -> submit()
        }
    }

    private fun updateLoginFields(event: LoginEvent.UpdateLoginFields) {
        state.value = event.loginState
    }

    private fun submit() {
        screenModelScope.launch {
            if (state.value.email.isEmpty() ||
                state.value.password.isEmpty()
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

            when (val result = authRepository.login(state.value.email, state.value.password)) {
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