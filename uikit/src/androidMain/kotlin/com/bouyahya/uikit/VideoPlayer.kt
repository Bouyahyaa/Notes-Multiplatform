package com.bouyahya.uikit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
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
            thumbColor =  MaterialTheme.colors.secondaryVariant,
            activeTrackColor =  MaterialTheme.colors.secondaryVariant,
            inactiveTrackColor = Color.White,
            textColor = Color.White,
            autoHideControl = true,
            controlHideInterval = 5
        )
    )
}