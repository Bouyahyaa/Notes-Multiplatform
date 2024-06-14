package com.bouyahya.notes.features.videos.domain

import kotlin.random.Random

data class Video(
    val id: Int = Random.nextInt(),
    val title: String,
    val url: String,
    val thumbnail: String,
)
