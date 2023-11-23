package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyrl29074.book_room_in_hotel.databinding.ItemPeculiarityBinding
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

class PeculiarityListItemAdapterDelegate : AdapterDelegate<DisplayableItem> {
    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is DisplayableItem.PeculiarityItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemPeculiarityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeculiarityViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<DisplayableItem>,
        position: Int
    ) {
        (holder as PeculiarityViewHolder).bind(items[position] as DisplayableItem.PeculiarityItem)
    }

    inner class PeculiarityViewHolder(private val binding: ItemPeculiarityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayableItem.PeculiarityItem) {
            binding.peculiarity.text = item.text
        }
    }
}