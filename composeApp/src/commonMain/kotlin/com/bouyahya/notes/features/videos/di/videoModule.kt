package com.bouyahya.notes.features.videos.di

import com.bouyahya.notes.features.videos.ui.VideosViewModel
import org.koin.dsl.module

val videoModule
    get() = module {
        single {
            VideosViewModel()
        }
    }