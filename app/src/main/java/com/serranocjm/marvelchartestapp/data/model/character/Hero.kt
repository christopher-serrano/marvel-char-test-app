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
    val comics: CommonResource?,
    @SerializedName("series")
    val series: CommonResource?,
    @SerializedName("stories")
    val stories: CommonResource?,
    @SerializedName("events")
    val events: CommonResource?,
    @SerializedName("urls")
    val urls: List<Url>?
)
