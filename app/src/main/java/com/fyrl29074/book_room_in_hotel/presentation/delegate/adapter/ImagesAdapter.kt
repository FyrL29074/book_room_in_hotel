package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter

import android.annotation.SuppressLint
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DelegationAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

class ImagesAdapter(
    adapterDelegatesManager: AdapterDelegatesManager<DisplayableItem>,
    override var items: List<DisplayableItem>
) : DelegationAdapter<DisplayableItem>(
    adapterDelegatesManager,
    items,
) {
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DisplayableItem.ImageItem>) {
        this.items = data
        notifyDataSetChanged()
    }
}