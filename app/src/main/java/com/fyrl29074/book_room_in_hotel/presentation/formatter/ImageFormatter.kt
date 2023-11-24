package com.fyrl29074.book_room_in_hotel.presentation.formatter

import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

object ImageFormatter {
    fun format(imageUrls: List<String>): List<DisplayableItem.ImageItem> {
        return imageUrls.map { url ->
            DisplayableItem.ImageItem(url)
        }
    }
}