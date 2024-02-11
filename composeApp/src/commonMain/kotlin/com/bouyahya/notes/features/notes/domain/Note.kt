package com.bouyahya.notes.features.notes.domain

data class Note(
    val id: Long,
    val title: String,
    val description: String
) {
    companion object {
        val default = Note(
            id = -1,
            title = "",
            description = ""
        )
    }
}
