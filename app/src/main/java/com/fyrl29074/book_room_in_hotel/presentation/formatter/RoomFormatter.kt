package com.fyrl29074.book_room_in_hotel.presentation.formatter

import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.domain.model.Rooms

object RoomFormatter {
    fun format(rooms: Rooms): List<DisplayableItem.RoomItem> {
        return rooms.rooms.map { room ->
            DisplayableItem.RoomItem(
                id = room.id,
                name = room.name,
                price = room.price,
                pricePer = room.pricePer,
                peculiarities = PeculiaritiesFormatter.format(room.peculiarities),
                imageUrls = ImageFormatter.format(
                    room.imageUrls
                ),
            )
        }
    }
}