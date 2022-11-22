package com.serranocjm.marvelchartestapp.network.api

import com.serranocjm.marvelchartestapp.data.model.wrappers.CharacterDataWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoints {

    @GET("characters/")
    suspend fun getCharacterList(): Response<CharacterDataWrapper?>

    @GET("characters/{id}")
    suspend fun getCharacterDetail(@Path(value = "id") id: Int): Response<CharacterDataWrapper?>
}
