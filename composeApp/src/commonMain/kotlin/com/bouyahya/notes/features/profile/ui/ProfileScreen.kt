package com.bouyahya.notes.features.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ProfileViewModel>()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.onEvent(ProfileEvent.GetProfile)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error.isNotEmpty()) {
                Text(text = state.error)
            } else {
                KamelImage(
                    resource = asyncPainterResource(
                        state.picture?.url ?: "https://placekitten.com/200/200"
                    ),
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
}