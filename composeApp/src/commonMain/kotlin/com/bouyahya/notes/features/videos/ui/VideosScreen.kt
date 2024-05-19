package com.bouyahya.notes.features.videos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject

@Composable
fun VideosScreen(
    viewModel: VideosViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        VideoPlayer(url = state.chosenVideo.url)

        state.videos.forEach { video ->
            VideoItem(
                video = video,
                selected = video.id == state.chosenVideo.id,
                onVideoChange = {
                    viewModel.onEvent(VideosEvent.ChangeVideo(video))
                },
            )
            HorizontalDivider()
        }
    }
}