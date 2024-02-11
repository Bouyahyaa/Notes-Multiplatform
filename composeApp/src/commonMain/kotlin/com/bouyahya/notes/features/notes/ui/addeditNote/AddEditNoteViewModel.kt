package com.bouyahya.notes.features.notes.ui.addeditNote

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.bouyahya.notes.core.utils.ValidationEvent
import com.bouyahya.notes.features.notes.domain.NoteRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class AddEditNoteViewModel(
    private val noteRepository: NoteRepository,
    val state: MutableStateFlow<AddEditNoteState> = MutableStateFlow(AddEditNoteState())
) : ScreenModel {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.UpdateNoteFields -> updateNoteFields(event)
            is AddEditNoteEvent.Submit -> submit()
        }
    }

    private fun updateNoteFields(event: AddEditNoteEvent.UpdateNoteFields) {
        state.value.note.value = event.note
    }

    private fun submit() {
        screenModelScope.launch {
            noteRepository.insertNote(
                state.value.note.value.copy(
                    id = Random.nextLong(0, 10000000L)
                )
            ).onSuccess {
                validationEventChannel.send(ValidationEvent.Success)
            }.onFailure {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }
}