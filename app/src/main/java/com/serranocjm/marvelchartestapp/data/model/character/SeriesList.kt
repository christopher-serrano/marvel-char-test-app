package com.serranocjm.marvelchartestapp.data.model.character

import com.google.gson.annotations.SerializedName

data class SeriesList(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<SeriesSummary?>?,
    @SerializedName("returned")
    val returned: Int?
)
