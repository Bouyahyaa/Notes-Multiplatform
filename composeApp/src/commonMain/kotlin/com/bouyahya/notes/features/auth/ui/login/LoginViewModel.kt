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
            is LoginEvent.UpdateEmail ->
                updateField(event.loginForm) {
                    it.copy(email = event.loginForm.email.validate())
                }

            is LoginEvent.UpdatePassword ->
                updateField(event.loginForm) {
                    it.copy(password = event.loginForm.password.validate())
                }

            is LoginEvent.Submit -> submit()
        }
    }


    private fun updateField(loginForm: LoginForm, update: (LoginForm) -> LoginForm) {
        state.value = state.value.copy(loginForm = update(loginForm))
    }

    private fun submit() {
        viewModelScope.launch {
            state.update {
                it.copy(
                    loginForm = state.value.loginForm.copy(
                        email = it.loginForm.email.validate(),
                        password = it.loginForm.password.validate()
                    )
                )
            }

            if (!state.value.loginForm.isValid) return@launch

            state.update {
                it.copy(
                    isLoading = true
                )
            }

            when (val result = authRepository.login(
                state.value.loginForm.email.value,
                state.value.loginForm.password.value
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