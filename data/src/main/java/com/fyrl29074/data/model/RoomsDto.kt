package com.fyrl29074.data.model

import com.google.gson.annotations.SerializedName

data class RoomsDto(
    @SerializedName("rooms")
    val rooms: List<RoomDto>,
)