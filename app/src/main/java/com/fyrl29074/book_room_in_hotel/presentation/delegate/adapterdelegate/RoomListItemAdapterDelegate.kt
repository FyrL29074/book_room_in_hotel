package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyrl29074.book_room_in_hotel.databinding.ItemRoomBinding
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.ImagesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.PeculiaritiesAdapter

class RoomListItemAdapterDelegate(
    private val onRoomClick: () -> Unit,
) : AdapterDelegate<DisplayableItem> {
    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is DisplayableItem.RoomItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<DisplayableItem>,
        position: Int
    ) {
        (holder as RoomViewHolder).bind(items[position] as DisplayableItem.RoomItem)
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(room: DisplayableItem.RoomItem) {
            with(binding) {
                name.text = room.name

                price.text = room.price.toString()
                pricePer.text = room.pricePer
                chooseRoom.setOnClickListener {
                    onRoomClick()
                }
            }
        }
    }
}