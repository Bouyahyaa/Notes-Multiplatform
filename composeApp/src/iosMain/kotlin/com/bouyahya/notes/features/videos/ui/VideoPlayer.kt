package com.bouyahya.notes.features.videos.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import chaintech.videoplayer.ui.VideoPlayerView
import chaintech.videoplayer.model.PlayerConfig

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String
) {
    VideoPlayerView(
        modifier = modifier,
        url = url,
        playerConfig = PlayerConfig(
            enablePauseResume = true,
            showSeekBar = false,
            showDuration = true,
            thumbColor = Color.Red,
            activeTrackColor = Color.Red,
            inactiveTrackColor = Color.White,
            textColor = Color.White,
            seekBarBottomPadding = 8.dp,
            pauseResumeIconSize = 32.dp,
            autoHideControl = true,
            controlHideInterval = 5
        )
    )
}