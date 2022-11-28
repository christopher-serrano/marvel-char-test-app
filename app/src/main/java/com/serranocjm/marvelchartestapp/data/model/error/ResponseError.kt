package com.serranocjm.marvelchartestapp.data.model.error


import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("status")
    val status: String?
)