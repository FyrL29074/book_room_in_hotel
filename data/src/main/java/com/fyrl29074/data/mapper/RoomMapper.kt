package com.fyrl29074.data.mapper

import com.fyrl29074.data.model.RoomDto
import com.fyrl29074.data.model.RoomsDto
import com.fyrl29074.domain.model.Room
import com.fyrl29074.domain.model.Rooms

object RoomMapper {
    fun map(dto: RoomsDto): Rooms {
        return Rooms(
            rooms = dto.rooms.map { room -> this.map(room) },
        )
    }

    private fun map(dto: RoomDto): Room {
        return Room(
            id = dto.id,
            name = dto.name,
            price = dto.price,
            imageUrls = dto.imageUrls,
            peculiarities = dto.peculiarities,
            pricePer = dto.pricePer,
        )
    }
}