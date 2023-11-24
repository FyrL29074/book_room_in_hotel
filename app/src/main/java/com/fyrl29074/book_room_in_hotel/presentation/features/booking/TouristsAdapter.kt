package com.fyrl29074.book_room_in_hotel.presentation.features.booking

import android.annotation.SuppressLint
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DelegationAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

class TouristsAdapter(
    adapterDelegatesManager: AdapterDelegatesManager<DisplayableItem>,
    override var items: List<DisplayableItem>
) : DelegationAdapter<DisplayableItem>(
    adapterDelegatesManager,
    items,
) {
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DisplayableItem.TouristItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addEmptyItem() {
        items = items + listOf(
            DisplayableItem.TouristItem(
                id = items.lastIndex + 1,
                name = "",
                surname = "",
                dateOfBirth = "",
                citizenship = "",
                foreignPassportNumber = "",
                foreignPassportExpiryDate = "",
            )
        )
        notifyDataSetChanged()
    }
}