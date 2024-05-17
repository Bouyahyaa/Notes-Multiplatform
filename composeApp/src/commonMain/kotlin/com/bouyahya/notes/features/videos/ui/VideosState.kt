package com.bouyahya.notes.features.videos.ui

import com.bouyahya.notes.features.videos.domain.Video

data class VideosState(
    val videos: List<Video> = listOf<Video>(
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            title = "BigBuckBunny"
        ),
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
            title = "WhatCarCanYouGetForAGrand"
        ),
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
            title = "ForBiggerEscapes"
        ),
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            title = "WeAreGoingOnBullrun"
        ),
    ),
    val chosenVideo: Video = videos.first()
)
