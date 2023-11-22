package com.fyrl29074.book_room_in_hotel.presentation.model.formatter

import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

object PeculiaritiesFormatter {
    fun format(peculiarities: List<String>): List<DisplayableItem.PeculiarityItem> {
        return peculiarities.map { peculiarity ->
            DisplayableItem.PeculiarityItem(peculiarity)
        }
    }
}