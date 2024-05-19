package com.bouyahya.notes.features.videos.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.VideoPlayerView

@Composable
actual fun VideoPlayer(url: String) {
    VideoPlayerView(
        modifier = Modifier.fillMaxWidth()
            .height(400.dp),
        url = url,
        playerConfig = PlayerConfig(
            enablePauseResume = true,
            showSeekBar = true,
            showDuration = true,
            thumbColor = Color.Red,
            activeTrackColor = Color.Red,
            inactiveTrackColor = Color.White,
            textColor = Color.White,
            autoHideControl = true,
            controlHideInterval = 5
        )
    )
}