package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface AdapterDelegate<T> {
    fun isForViewType(items: List<T>, position: Int): Boolean
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, items: List<T>, position: Int)
}