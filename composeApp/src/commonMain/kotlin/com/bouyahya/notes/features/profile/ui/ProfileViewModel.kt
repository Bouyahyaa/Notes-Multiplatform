package com.bouyahya.notes.features.profile.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.bouyahya.notes.features.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    val state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
) : ScreenModel {
    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfile -> getProfile()
        }
    }

    private fun getProfile() {
        screenModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }

            profileRepository.getRandomPicture()
                .onSuccess { picture ->
                    state.update {
                        it.copy(
                            picture = picture
                        )
                    }
                }.onFailure {
                    state.update {
                        it.copy(
                            error = "Something went wrong!"
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