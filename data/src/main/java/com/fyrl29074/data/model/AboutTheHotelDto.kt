package com.fyrl29074.data.model

import com.google.gson.annotations.SerializedName

data class AboutTheHotelDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>,
)
