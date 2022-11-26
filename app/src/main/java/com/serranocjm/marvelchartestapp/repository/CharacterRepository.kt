package com.serranocjm.marvelchartestapp.repository

import androidx.paging.PagingData
import com.serranocjm.marvelchartestapp.data.model.character.Hero
import com.serranocjm.marvelchartestapp.ui.adapter.item.model.HeroItemModel
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterList(offset: Int): List<Hero>?
    suspend fun getCharacterDetail(id: Int): Hero?
    fun getCharacterListFlow(offset: Int): Flow<PagingData<HeroItemModel>>
}
