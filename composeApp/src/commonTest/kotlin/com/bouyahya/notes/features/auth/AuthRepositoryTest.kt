package com.bouyahya.notes.features.auth

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isNotEmpty
import com.bouyahya.notes.features.auth.data.remote.AuthRemoteDataSource
import com.bouyahya.notes.features.auth.data.remote.login.LoginRequest
import com.bouyahya.notes.features.auth.data.remote.login.LoginResponse
import com.bouyahya.notes.features.auth.data.repository.AuthRepositoryImpl
import com.bouyahya.notes.features.auth.domain.repository.AuthRepository
import com.russhwolf.settings.Settings
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class AuthRepositoryTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var mockAuthRemoteDataSource: AuthRemoteDataSource

    private val settings = Settings()
    private val loginRequest = LoginRequest("email", "password")
    private val loginResponse = LoginResponse(token)

    @BeforeTest
    fun setUp() {
        settings.clear()
        mockAuthRemoteDataSource = mock<AuthRemoteDataSource>()
        authRepository = AuthRepositoryImpl(mockAuthRemoteDataSource, settings)
    }

    @Test
    fun `Settings have the token after login success`() = runTest {
        //arrange
        everySuspend {
            mockAuthRemoteDataSource.login(loginRequest)
        } returns loginResponse

        //act
        authRepository.login("email", "password")

        //verify
        assertThat(settings.getString("token", "")).isNotEmpty()
    }

    @Test
    fun `Settings have no token after login error`() = runTest {
        //arrange
        everySuspend {
            mockAuthRemoteDataSource.login(loginRequest)
        } throws Exception()

        //act
        authRepository.login("email", "password")

        //verify
        assertThat(settings.getString("token", "")).isEmpty()
    }

    companion object {
        const val token: String = "TOKEN"
    }
}