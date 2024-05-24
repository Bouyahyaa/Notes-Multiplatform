package com.bouyahya.notes.features.profile.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bouyahya.notes.features.profile.ui.components.ProfileShimmer
import com.bouyahya.notes.navigation.Graph
import com.bouyahya.notes.navigation.LocalNavController
import com.bouyahya.notes.permissions.shared.SharedManager
import com.valentinilk.shimmer.shimmer
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinInject(),
) {
    val state by viewModel.state.collectAsState()
    val rootNavController = LocalNavController.current

    var imageSourceOptionDialog by remember { mutableStateOf(value = false) }

    SharedManager(
        imageSourceOptionDialog = imageSourceOptionDialog,
        onChangeImageBitmap = {
            if (it != null)
                viewModel.onEvent(ProfileEvent.SetImage(it))
        },
        onChangeImageSourceOptionDialog = {
            imageSourceOptionDialog = it
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        if (state.isLoading) {
            ProfileShimmer(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            Button(
                onClick = {
                    viewModel.onEvent(ProfileEvent.Logout)
                    rootNavController.navigate(Graph.AUTH) {
                        popUpTo(Graph.HOME) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryVariant,
                ),
                modifier = Modifier
                    .align(Alignment.TopStart)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center)
            ) {
                if (state.imageBitmap != null)
                    Image(
                        bitmap = state.imageBitmap!!,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .clickable {
                                imageSourceOptionDialog = true
                            },
                        contentScale = ContentScale.Crop
                    )
                else
                    KamelImage(
                        resource = asyncPainterResource(
                            state.picture?.url ?: "https://placekitten.com/200/200"
                        ),
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
                            .clickable {
                                imageSourceOptionDialog = true
                            }
                    )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.picture?.username ?: "Bilel Bouyahya",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Kotlin Developer",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Gray
                )

                if (state.picture == null) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            viewModel.onEvent(ProfileEvent.GetProfile)
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.secondaryVariant,
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = "Get Unsplash Picture",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}