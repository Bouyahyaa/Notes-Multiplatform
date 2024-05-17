package com.bouyahya.notes.features.videos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent
import java.awt.Component
import java.util.*


@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    Column {
        VideoPlayerImpl(
            url = url,
            modifier = Modifier.fillMaxWidth()
                .height(400.dp)
        )
    }
}

@Composable
fun VideoPlayerImpl(
    url: String,
    modifier: Modifier,
) {
    Text("VideoPlayer NotImplemented yet for desktop")
//    val mediaPlayerComponent = remember { initializeMediaPlayerComponent() }
//    val mediaPlayer = remember { mediaPlayerComponent.mediaPlayer() }
//
//    val factory = remember { { mediaPlayerComponent } }
//    /* OR the following code and using SwingPanel(factory = { factory }, ...) */
//    // val factory by rememberUpdatedState(mediaPlayerComponent)
//
//    LaunchedEffect(url) { mediaPlayer.media().play/*OR .start*/(url) }
//    DisposableEffect(Unit) { onDispose(mediaPlayer::release) }
//    SwingPanel(
//        factory = factory,
//        background = Color.Transparent,
//        modifier = modifier
//    )
}

private fun initializeMediaPlayerComponent(): Component {
    NativeDiscovery().discover()
    return if (isMacOS()) {
        CallbackMediaPlayerComponent()
    } else {
        EmbeddedMediaPlayerComponent()
    }
}

private fun Component.mediaPlayer() = when (this) {
    is CallbackMediaPlayerComponent -> mediaPlayer()
    is EmbeddedMediaPlayerComponent -> mediaPlayer()
    else -> error("mediaPlayer() can only be called on vlcj player components")
}

private fun isMacOS(): Boolean {
    val os = System
        .getProperty("os.name", "generic")
        .lowercase(Locale.ENGLISH)
    return "mac" in os || "darwin" in os
}