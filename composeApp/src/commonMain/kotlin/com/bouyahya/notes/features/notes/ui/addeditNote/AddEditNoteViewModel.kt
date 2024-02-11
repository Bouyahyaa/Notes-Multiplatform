package com.bouyahya.notes.features.notes.ui.addeditNote

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.features.notes.domain.NoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class AddEditNoteViewModel(
    private val noteRepository: NoteRepository,
    val state: MutableStateFlow<AddEditNoteState> = MutableStateFlow(AddEditNoteState()),
    val noteId: Long?,
) : ScreenModel {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    init {
        if (noteId != null) {
            getNote()
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.UpdateNoteFields -> updateNoteFields(event)
            is AddEditNoteEvent.Submit -> submit()
        }
    }

    private fun getNote() {
        screenModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository
                .getNoteById(noteId!!)
                .onSuccess {
                    state.value.note.value = it
                }.onFailure {
                    validationEventChannel.send(
                        ValidationEvent.Failure(
                            message = "Something went wrong!"
                        )
                    )
                }
        }.invokeOnCompletion {
            state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun updateNoteFields(event: AddEditNoteEvent.UpdateNoteFields) {
        state.value.note.value = event.note
    }

    private fun submit() {
        screenModelScope.launch {
            val note = state.value.note.value

            if (note.title.isEmpty() || note.description.isEmpty()) {
                validationEventChannel.send(
                    ValidationEvent.Failure(
                        message = "Title and description are required"
                    )
                )
                return@launch
            }

            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository.insertNote(
                if (noteId == null)
                    note.copy(id = Random.nextLong(0, 10000000L)) else
                    note
            ).onSuccess {
                validationEventChannel.send(ValidationEvent.Success)
            }.onFailure {
                validationEventChannel.send(
                    ValidationEvent.Failure(
                        message = "Something went wrong!"
                    )
                )
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