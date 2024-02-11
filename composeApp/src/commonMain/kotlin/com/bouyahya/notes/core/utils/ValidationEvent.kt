package com.bouyahya.notes.core.utils

sealed interface ValidationEvent {
    data object Success : ValidationEvent
    data object Failure : ValidationEvent
}
