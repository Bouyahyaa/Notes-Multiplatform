package com.bouyahya.notes.features.videos.ui

import com.bouyahya.notes.features.videos.domain.Video

sealed interface VideosEvent {
    data class ChangeVideo(val video: Video) : VideosEvent
}