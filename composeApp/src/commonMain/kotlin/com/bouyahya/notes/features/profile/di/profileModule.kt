package com.bouyahya.notes.features.profile.di

import com.bouyahya.notes.features.profile.data.remote.UnsplashClient
import com.bouyahya.notes.features.profile.data.repository.ProfileRepositoryImpl
import com.bouyahya.notes.features.profile.domain.repository.ProfileRepository
import com.bouyahya.notes.features.profile.ui.ProfileViewModel
import org.koin.dsl.module

val profileModule
    get() = module {
        single { UnsplashClient(httpClient = get()) }
        single<ProfileRepository> {
            ProfileRepositoryImpl(
                unsplashClient = get()
            )
        }

        factory {
            ProfileViewModel(
                profileRepository = get(),
            )
        }
    }