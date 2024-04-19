package com.bouyahya.notes.core.utils

sealed interface Result<out D> {
    data class Success<out D>(val data: D) : Result<D>
    data class Failure<out D>(val error: String) : Result<D>
}