package com.bouyahya.notes.features.videos.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bouyahya.notes.features.videos.domain.Video
import com.valentinilk.shimmer.shimmer
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun VideoItem(
    video: Video,
    selected: Boolean,
    onVideoChange: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = if (selected)
                    MaterialTheme.colors.secondaryVariant.copy(
                        alpha = 0.5F
                    ) else
                    Color.Transparent,
            )
            .clickable(enabled = !selected) {
                onVideoChange()
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        KamelImage(
            resource = asyncPainterResource(video.thumbnail),
            animationSpec = tween(),
            onLoading = { _ ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(64.dp)
                        .shimmer(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(64.dp)
                            .background(Color.Gray)
                    )
                }
            },
            contentScale = ContentScale.Crop,
            contentDescription = "Video thumbnail",
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            BasicText(
                text = video.title,
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp)
            )

            BasicText(
                text = video.title,
                style = MaterialTheme.typography.body2.copy(color = Color.Red, fontSize = 14.sp)
            )
        }
    }
}