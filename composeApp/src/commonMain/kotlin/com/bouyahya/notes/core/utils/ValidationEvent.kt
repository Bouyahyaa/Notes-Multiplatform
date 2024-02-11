package com.bouyahya.notes.core.utils

sealed interface ValidationEvent {
    data object Success : ValidationEvent
    data class Failure(val message: String) : ValidationEvent
}
