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

            is AddEditNoteEvent.UpdateTitle -> updateField(event.addEditNoteForm) {
                it.copy(title = event.addEditNoteForm.title.validate())
            }

            is AddEditNoteEvent.UpdateDescription -> updateField(event.addEditNoteForm) {
                it.copy(description = event.addEditNoteForm.description.validate())
            }

            is AddEditNoteEvent.Submit -> submit(event)
        }
    }

    private fun updateField(addEditNoteForm: AddEditNoteForm, update: (AddEditNoteForm) -> AddEditNoteForm) {
        state.value = state.value.copy(addEditNoteForm = update(addEditNoteForm))
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
                        it.copy(
                            addEditNoteForm = state.value.addEditNoteForm.copy(
                                title = state.value.addEditNoteForm.title.copy(value = note.title),
                                description = state.value.addEditNoteForm.description.copy(value = note.description),
                            )
                        )
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
            state.update {
                it.copy(
                    addEditNoteForm = state.value.addEditNoteForm.copy(
                        title = it.addEditNoteForm.title.validate(),
                        description = it.addEditNoteForm.description.validate()
                    )
                )
            }

            if (!state.value.addEditNoteForm.isValid) return@launch

            val note = state.value.addEditNoteForm.note

            state.update {
                it.copy(
                    isLoading = true
                )
            }
            noteRepository.insertNote(
                if (event.noteId != -1L)
                    note.copy(id = event.noteId) else
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