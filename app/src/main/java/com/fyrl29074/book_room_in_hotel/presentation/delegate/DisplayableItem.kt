package com.fyrl29074.book_room_in_hotel.presentation.delegate

sealed class DisplayableItem {
    data class ImageItem(val url: String) : DisplayableItem()
    data class PeculiarityItem(val text: String) : DisplayableItem()
}