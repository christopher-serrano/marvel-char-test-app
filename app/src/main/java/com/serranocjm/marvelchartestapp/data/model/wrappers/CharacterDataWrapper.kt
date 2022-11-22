package com.serranocjm.marvelchartestapp.data.model.wrappers

import com.google.gson.annotations.SerializedName
import com.serranocjm.marvelchartestapp.data.model.* // ktlint-disable no-wildcard-imports
import com.serranocjm.marvelchartestapp.data.model.character.CharacterDataContainer

data class CharacterDataWrapper(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("attributionText")
    val attributionText: String?,
    @SerializedName("attributionHTML")
    val attributionHTML: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("data")
    val data: CharacterDataContainer?
)
