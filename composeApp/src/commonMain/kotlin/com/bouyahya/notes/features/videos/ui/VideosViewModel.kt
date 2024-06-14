package com.bouyahya.notes.features.videos.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class VideosViewModel(
    val state: MutableStateFlow<VideosState> = MutableStateFlow(VideosState())
) : ViewModel() {

    fun onEvent(event: VideosEvent) {
        when (event) {
            is VideosEvent.ChangeVideo -> changeVideo(event)
        }
    }

    private fun changeVideo(event: VideosEvent.ChangeVideo) {
        state.update {
            it.copy(
                chosenVideo = event.video
            )
        }
    }
}