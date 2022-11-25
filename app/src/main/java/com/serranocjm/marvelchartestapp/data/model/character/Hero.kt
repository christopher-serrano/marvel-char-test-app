package com.serranocjm.marvelchartestapp.data.model.character

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("thumbnail")
    val thumbnail: Image?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("comics")
    val comics: CommonResourceDetail?,
    @SerializedName("series")
    val series: CommonResourceDetail?,
    @SerializedName("stories")
    val stories: CommonResourceDetail?,
    @SerializedName("events")
    val events: CommonResourceDetail?,
    @SerializedName("urls")
    val urls: List<Url>?
)
