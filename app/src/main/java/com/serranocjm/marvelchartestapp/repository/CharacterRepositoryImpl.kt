package com.serranocjm.marvelchartestapp.repository

import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.network.api.ApiClient
import com.serranocjm.marvelchartestapp.utils.general.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent

class CharacterRepositoryImpl : CharacterRepository, KoinComponent {

    private val apiClient = ApiClient.invoke()

    override suspend fun getCharacterList(offset: Int): List<Hero?>? = withContext(Dispatchers.IO) {
        val response = apiClient.getCharacterList(QUERY_LIMIT, offset)
        response.body()?.data?.results
    }

    // Since the response is the same as the one for the character list, I only need the first item
    // of the results.
    override suspend fun getCharacterDetail(id: Int): Hero? = withContext(Dispatchers.IO) {
        val response = apiClient.getCharacterDetail(id)
        response.body()?.data?.results?.get(0)
    }

    // No intention to change the limit or offset for the time being
    companion object {
        const val QUERY_LIMIT = Constants.API_QUERY_LIMIT
    }
}
