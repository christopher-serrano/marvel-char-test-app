package com.serranocjm.marvelchartestapp.repository

import com.serranocjm.marvelchartestapp.data.model.character.Hero

interface CharacterRepository {
    suspend fun getCharacterList(offset: Int): List<Hero?>?
    suspend fun getCharacterDetail(id: Int): Hero?
}
