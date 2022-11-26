package com.serranocjm.marvelchartestapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.network.api.ApiClient
import com.serranocjm.marvelchartestapp.repository.paging.HeroListPagingSource
import com.serranocjm.marvelchartestapp.utils.general.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterRepositoryImpl : CharacterRepository, KoinComponent {

    private val apiClient = ApiClient.invoke()
    private val heroListDataSource: HeroListPagingSource by inject()

    override suspend fun getCharacterList(offset: Int): List<Hero>? = withContext(Dispatchers.IO) {
        val response = apiClient.getCharacterList(Constants.API_QUERY_LIMIT, offset)
        response.body()?.data?.results
    }

    // Since the response is the same as the one for the character list, I only need the first item
    // of the results.
    override suspend fun getCharacterDetail(id: Int): Hero? = withContext(Dispatchers.IO) {
        val response = apiClient.getCharacterDetail(id)
        response.body()?.data?.results?.get(0)
    }

    override fun getCharacterListFlow(offset: Int): Flow<PagingData<Hero>> =
        Pager(
            PagingConfig(
                pageSize = offset,
                initialLoadSize = offset
            )
        ) {
            heroListDataSource
        }.flow
}
