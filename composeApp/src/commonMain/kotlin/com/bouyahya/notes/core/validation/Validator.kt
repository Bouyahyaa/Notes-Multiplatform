package com.bouyahya.notes.core.validation

interface Validator<T> {
    fun execute(value: T): String?
}