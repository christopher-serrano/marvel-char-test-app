package com.serranocjm.marvelchartestapp.data.model.character

import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)
