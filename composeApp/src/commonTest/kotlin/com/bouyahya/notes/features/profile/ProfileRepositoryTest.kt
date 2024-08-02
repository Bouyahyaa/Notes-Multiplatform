package com.bouyahya.notes.features.profile

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.bouyahya.notes.core.network.MockClient
import com.bouyahya.notes.core.network.MockResponse
import com.bouyahya.notes.core.network.testKtorClient
import com.bouyahya.notes.features.profile.data.remote.UnsplashClient
import com.bouyahya.notes.features.profile.data.repository.ProfileRepositoryImpl
import com.bouyahya.notes.features.profile.domain.model.Picture
import com.bouyahya.notes.features.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ProfileRepositoryTest {
    private val mockClient = MockClient()
    private val ktorClient = testKtorClient(mockClient)

    private lateinit var unsplashClient: UnsplashClient
    private lateinit var profileRepository: ProfileRepository

    private val jsonResponse = """
                {
                    "id": "1",
                    "description": "description",
                    "user": {
                        "username": "username",
                        "name": "name"
                    },
                    "urls": {
                        "regular": "https://example.com/image.jpg"
                    }
                }
                """.trimIndent()

    private val testPictureModel = Picture(
        id = "1",
        description = "description",
        username = "username",
        name = "name",
        url = "https://example.com/image.jpg"
    )

    @BeforeTest
    fun setUp() {
        unsplashClient = UnsplashClient(ktorClient)
        profileRepository = ProfileRepositoryImpl(unsplashClient)
    }

    @Test
    fun `Should return testPictureModel`() = runTest {
        //arrange
        val response = MockResponse.ok(jsonResponse)
        mockClient.setResponse(response)

        //act
        val result = profileRepository.getRandomPicture()

        //verify
        assertThat(result.getOrNull()).isEqualTo(testPictureModel)
    }
}