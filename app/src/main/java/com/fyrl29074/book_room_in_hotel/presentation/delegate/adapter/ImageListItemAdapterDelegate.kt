package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fyrl29074.book_room_in_hotel.databinding.ItemImageBinding
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

class ImageListItemAdapterDelegate : AdapterDelegate<DisplayableItem> {
    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is DisplayableItem.ImageItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<DisplayableItem>,
        position: Int
    ) {
        (holder as ImageViewHolder).bind(items[position] as DisplayableItem.ImageItem)
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayableItem.ImageItem) {
            Glide.with(binding.root.context)
                .load(item.url)
                .into(binding.image)
        }
    }
}