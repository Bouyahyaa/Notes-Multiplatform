package com.bouyahya.notes.features.profile.ui

sealed interface ProfileEvent {
    data object GetProfile : ProfileEvent
    data object Logout : ProfileEvent
}