package com.serranocjm.marvelchartestapp.repository

import android.content.Context
import com.serranocjm.marvelchartestapp.data.model.wrappers.CharacterDataWrapper
import com.serranocjm.marvelchartestapp.di.modules.networkModule
import com.serranocjm.marvelchartestapp.di.modules.repositoryModule
import com.serranocjm.marvelchartestapp.network.api.Endpoints
import com.serranocjm.marvelchartestapp.utils.API_OFFSET_SIZE
import com.serranocjm.marvelchartestapp.utils.API_QUERY_LIMIT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CharacterRepositoryTest : KoinTest {

    @Before
    fun setUp() {
        startKoin {
            androidContext(mock(Context::class.java))
            modules(networkModule, repositoryModule)
        }
    }

    @Test
    fun getCharacterListTest() = runTest(UnconfinedTestDispatcher()) {
        // Mock network classes
        val endpointsMock = mock(Endpoints::class.java)

        // Mock model classes
        val characterDataWrapperMock = mock(CharacterDataWrapper::class.java)

        // Mock character repository implementation
        val characterRepositoryMock = CharacterRepositoryImpl()

        // Test api call
        `when`(endpointsMock.getCharacterList(API_QUERY_LIMIT, API_OFFSET_SIZE)).thenReturn(
            Response.success(characterDataWrapperMock)
        )

        // Assert the response is successful
        val characterListResponse = endpointsMock.getCharacterList(API_QUERY_LIMIT, API_OFFSET_SIZE)

        // Then
        assertThat(characterListResponse.isSuccessful, `is`(true))

        // Assert the resulting data is not an empty or null list
        val characterList = characterRepositoryMock.getCharacterList(API_OFFSET_SIZE)

        // Then
        assert(!characterList.isNullOrEmpty())
    }

    @Test
    fun getCharacterDetailSuccessTest() = runTest(UnconfinedTestDispatcher()) {
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

    @Test
    fun getCharacterDetailErrorTest() {
        // Parameter values
        val characterId = 0

        // Mock network classes
        val endpointsMock = mock(Endpoints::class.java)

        // Mock model classes
        val characterDataWrapperMock = mock(CharacterDataWrapper::class.java)

        // Mock character repository implementation
        val characterRepositoryMock = CharacterRepositoryImpl()
    }
}
