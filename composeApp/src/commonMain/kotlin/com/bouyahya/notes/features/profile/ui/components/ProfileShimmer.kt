package com.bouyahya.notes.features.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun ProfileShimmer() {
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

    Spacer(modifier = Modifier.height(16.dp))

    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .fillMaxWidth()
            .height(10.dp)
            .shimmer(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth(0.5f)
                .height(10.dp)
                .background(Color.Gray)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .fillMaxWidth(0.5f)
            .height(10.dp)
            .shimmer(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxWidth(0.5f)
                .height(10.dp)
                .background(Color.Gray)
        )
    }
}