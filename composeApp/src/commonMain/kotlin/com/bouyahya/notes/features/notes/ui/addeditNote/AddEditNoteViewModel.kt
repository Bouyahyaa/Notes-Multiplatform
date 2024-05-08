package com.bouyahya.notes.features.notes.ui.addeditNote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class AddEditNoteViewModel(
    private val noteRepository: NoteRepository,
    val state: MutableStateFlow<AddEditNoteState> = MutableStateFlow(AddEditNoteState()),
) : ViewModel() {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.GetNote -> getNote(event)

            is AddEditNoteEvent.UpdateNoteFields -> updateNoteFields(event)

            is AddEditNoteEvent.Submit -> submit(event)
        }
    }

    private fun updateNoteFields(event: AddEditNoteEvent.UpdateNoteFields) {
        state.value = state.value.copy(note = event.note)
    }

    private fun getNote(event: AddEditNoteEvent.GetNote) {
        viewModelScope.launch {
            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository
                .getNoteById(event.noteId)
                .onSuccess { note ->
                    state.update {
                        it.copy(note = note)
                    }
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

    private fun submit(event: AddEditNoteEvent.Submit) {
        viewModelScope.launch {
            val note = state.value.note

            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository.insertNote(
                if (event.noteId != -1L)
                    note else
                    note.copy(id = Random.nextLong(0, 10000000L))
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