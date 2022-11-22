package com.serranocjm.marvelchartestapp.data.model.character

import com.google.gson.annotations.SerializedName

data class EventList(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<EventSummary?>?,
    @SerializedName("returned")
    val returned: Int?
) 
