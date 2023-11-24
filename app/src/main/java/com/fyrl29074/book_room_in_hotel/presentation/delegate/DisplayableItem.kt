package com.fyrl29074.book_room_in_hotel.presentation.delegate

sealed class DisplayableItem {
    data class ImageItem(val url: String) : DisplayableItem()
    data class PeculiarityItem(val text: String) : DisplayableItem()
    data class RoomItem(
        val id: Int,
        val name: String,
        val price: Int,
        val pricePer: String,
        val peculiarities: List<PeculiarityItem>,
        val imageUrls: List<ImageItem>,
    ) : DisplayableItem()

    data class TouristItem(
        val id: Int,
        val name: String,
        val surname: String,
        val dateOfBirth: String,
        val citizenship: String,
        val foreignPassportNumber: String,
        val foreignPassportExpiryDate: String,
    ) : DisplayableItem()
}