package com.bouyahya.notes.core.validation

data class Field<T>(
    val value: T,
    val error: String? = null,
    val validators: List<Validator<T>>? = null
) {
    fun validate(): Field<T> {
        val error = validators?.firstNotNullOfOrNull { it.execute(value) }
        return copy(error = error)
    }

    fun reset(): Field<T> {
        return copy(error = null)
    }
}
