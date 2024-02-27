package com.bouyahya.notes.features.profile.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bouyahya.notes.features.profile.ui.components.ProfileShimmer
import com.valentinilk.shimmer.shimmer
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun ProfileScreen(
    state: ProfileState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isLoading) {
            ProfileShimmer()
        } else if (state.error.isNotEmpty()) {
            Text(text = state.error)
        } else {
            KamelImage(
                resource = asyncPainterResource(state.picture?.url ?: "https://placekitten.com/200/200"),
                animationSpec = tween(),
                onLoading = { _ ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .shimmer(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(100.dp)
                                .background(Color.Gray)
                        )
                    }
                },
                contentScale = ContentScale.Crop,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.picture?.username ?: "John Doe",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Kotlin Developer",
                style = MaterialTheme.typography.subtitle1,
                color = Color.Gray
            )
        }
    }
}