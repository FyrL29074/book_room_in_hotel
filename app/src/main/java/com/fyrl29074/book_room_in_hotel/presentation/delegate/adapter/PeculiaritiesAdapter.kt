package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter

import android.annotation.SuppressLint
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DelegationAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

class PeculiaritiesAdapter(
    adapterDelegatesManager: AdapterDelegatesManager<DisplayableItem>,
    override var items: List<DisplayableItem>
) : DelegationAdapter<DisplayableItem>(
    adapterDelegatesManager,
    items,
) {
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DisplayableItem.PeculiarityItem>) {
        this.items = data
        notifyDataSetChanged()
    }
}
