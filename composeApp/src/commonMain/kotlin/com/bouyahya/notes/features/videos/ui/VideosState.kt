package com.bouyahya.notes.features.videos.ui

import com.bouyahya.notes.features.videos.domain.Video

data class VideosState(
    val videos: List<Video> = listOf<Video>(
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            title = "BigBuckBunny",
            thumbnail = "https://images.unsplash.com/photo-1585110396000-c9ffd4e4b308?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
            title = "WhatCarCanYouGetForAGrand",
            thumbnail = "https://images.unsplash.com/photo-1650025036938-26cb60d17ecd?q=80&w=3071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4",
            title = "ForBiggerEscapes",
            thumbnail = "https://images.unsplash.com/photo-1588217885773-2f27c9c3b1c2?q=80&w=3095&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
        Video(
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4",
            title = "WeAreGoingOnBullrun",
            thumbnail = "https://images.unsplash.com/photo-1502224562085-639556652f33?q=80&w=2928&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        ),
    ),
    val chosenVideo: Video = videos.first()
)
