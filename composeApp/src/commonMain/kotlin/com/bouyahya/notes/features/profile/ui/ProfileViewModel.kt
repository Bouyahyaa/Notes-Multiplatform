package com.bouyahya.notes.features.profile.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.bouyahya.notes.features.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ScreenModel {
    init {
        screenModelScope.launch {
            profileRepository.getRandomPicture()
                .onSuccess {
                    println("unsplash $it")
                }.onFailure {
                    println("unsplash $it")
                }
        }
    }
}