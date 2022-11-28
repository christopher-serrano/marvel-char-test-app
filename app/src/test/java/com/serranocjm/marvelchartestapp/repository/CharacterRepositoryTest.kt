package com.serranocjm.marvelchartestapp.repository

import android.content.Context
import com.nhaarman.mockitokotlin2.verify
import com.serranocjm.marvelchartestapp.base.BaseUTTest
import com.serranocjm.marvelchartestapp.data.model.error.ResponseError
import com.serranocjm.marvelchartestapp.data.model.wrappers.CharacterDataWrapper
import com.serranocjm.marvelchartestapp.di.modules.networkModule
import com.serranocjm.marvelchartestapp.di.modules.repositoryModule
import com.serranocjm.marvelchartestapp.network.api.Endpoints
import com.serranocjm.marvelchartestapp.utils.API_OFFSET
import com.serranocjm.marvelchartestapp.utils.API_QUERY_LIMIT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CharacterRepositoryTest : KoinTest, BaseUTTest() {

    override fun setUp() {
        super.setUp()
        startKoin {
            androidContext(mock(Context::class.java))
            modules(networkModule, repositoryModule)
        }
    }

    /**
     * Testing with live api data
     */

    @Test
    fun get_character_list_retrofit_test() = runTest(UnconfinedTestDispatcher()) {
        // Mock network classes
        val endpointsMock = mock(Endpoints::class.java)

        // Mock model classes
        val characterDataWrapperMock = mock(CharacterDataWrapper::class.java)

        // Mock character repository implementation
        val characterRepositoryMock = CharacterRepositoryImpl()

        // Test api call
        `when`(endpointsMock.getCharacterList(API_QUERY_LIMIT, API_OFFSET)).thenReturn(
            Response.success(characterDataWrapperMock)
        )

        // Assert the response is successful
        val characterListResponse = endpointsMock.getCharacterList(API_QUERY_LIMIT, API_OFFSET)

        // Then
        assertThat(characterListResponse.isSuccessful, `is`(true))

        // Assert the resulting data is not an empty or null list
        val characterList = characterRepositoryMock.getCharacterList(API_OFFSET)

        // Then
        assert(!characterList.isNullOrEmpty())
    }

    @Test
    fun get_character_detail_retrofit_test() = runTest(UnconfinedTestDispatcher()) {
        // Parameter values
        val characterId = 1011334

        // Mock network classes
        val endpointsMock = mock(Endpoints::class.java)

        // Mock model classes
        val characterDataWrapperMock = mock(CharacterDataWrapper::class.java)

        // Mock character repository implementation
        val characterRepositoryMock = CharacterRepositoryImpl()

        // Test api call
        `when`(endpointsMock.getCharacterDetail(characterId)).thenReturn(
            Response.success(characterDataWrapperMock)
        )

        // Assert the response is successful
        val characterResponse = endpointsMock.getCharacterDetail(characterId)

        // Then
        assertThat(characterResponse.isSuccessful, `is`(true))

        // Assert that the character object is not null or empty
        val character = characterRepositoryMock.getCharacterDetail(characterId)

        // Then
        assertNotNull(character)
    }

    /**
     * Testing the exceptions with a mock server since the current MVVM pattern used doesn't
     * encapsulate the server error response, it simply captures the exception and extracts the
     * Retrofit error message
     */

    @Test
    fun character_not_found_mock_server_test() = runTest(UnconfinedTestDispatcher()) {
        // Parameter values
        val characterId = 1

        // Mock response classes
        val responseErrorMock = mock(ResponseError::class.java)
        val retrofitResponseMock = mock(Response::class.java)

        // Mock network classes
        val endpointsMock = mock(Endpoints::class.java)

        // Enqueue call and set response
        mockNetworkResponseWithFileContent("404_response.json", 404)

        // Verify response and assert
        verify(1) { endpointsMock.getCharacterDetail(characterId) }
        assertEquals(responseErrorMock.code, retrofitResponseMock.code())
    }

    @Test
    fun other_exception_mock_server_test() = runTest(UnconfinedTestDispatcher()) {
        // Parameter values
        val characterId = 0

        // Mock response classes
        val responseErrorMock = mock(ResponseError::class.java)
        val retrofitResponseMock = mock(Response::class.java)

        // Mock network classes
        val endpointsMock = mock(Endpoints::class.java)

        // Enqueue call and set response
        mockNetworkResponseWithFileContent("409_generic.json", 409)

        // Verify response and assert
        verify(1) { endpointsMock.getCharacterDetail(characterId) }
        assertEquals(responseErrorMock.code, retrofitResponseMock.code())
    }
}
