package com.fyrl29074.data.model

import com.google.gson.annotations.SerializedName

data class RoomDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("price_per")
    val pricePer: String,
    @SerializedName("peculiarities")
    val peculiarities: List<String>,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
)