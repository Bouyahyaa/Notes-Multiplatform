package com.bouyahya.notes.features.auth.di

import com.bouyahya.notes.features.auth.data.remote.AuthApi
import com.bouyahya.notes.features.auth.data.remote.AuthRemoteDataSource
import com.bouyahya.notes.features.auth.data.repository.AuthRepositoryImpl
import com.bouyahya.notes.features.auth.domain.repository.AuthRepository
import com.bouyahya.notes.features.auth.ui.login.LoginViewModel
import com.bouyahya.notes.features.splash.SplashViewModel
import org.koin.dsl.module

val authModule
    get() = module {
        single { AuthApi(client = get()) }
        single { AuthRemoteDataSource(authApi = get()) }
        single<AuthRepository> {
            AuthRepositoryImpl(
                authRemoteDataSource = get(),
                settings = get()
            )
        }

        factory {
            SplashViewModel(
                settings = get(),
            )
        }

        factory {
            LoginViewModel(
                authRepository = get(),
            )
        }
    }