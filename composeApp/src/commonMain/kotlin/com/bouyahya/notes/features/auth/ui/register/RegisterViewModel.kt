package com.bouyahya.notes.features.auth.ui.register

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

class RegisterViewModel(
    private val authRepository: AuthRepository,
    val state: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState()),
) : ViewModel() {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.UpdateRegisterForm -> updateRegisterForm(event)
            is RegisterEvent.Submit -> submit()
        }
    }


    private fun updateRegisterForm(event: RegisterEvent.UpdateRegisterForm) {
        state.value = state.value.copy(registerForm = event.registerForm)
    }

    private fun submit() {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }

            when (val result = authRepository.register(
                state.value.registerForm.name,
                state.value.registerForm.email,
                state.value.registerForm.password,
                state.value.registerForm.confirmPassword,
                state.value.registerForm.phone
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