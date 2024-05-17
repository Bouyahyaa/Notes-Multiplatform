package com.bouyahya.notes.features.profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bouyahya.notes.features.profile.domain.repository.ProfileRepository
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    private val settings: Settings,
    val state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
) : ViewModel() {

    init {
        getProfile()
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetProfile -> getProfile()
            is ProfileEvent.Logout -> logout()
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
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

    private fun logout() {
        settings.clear()
    }
}