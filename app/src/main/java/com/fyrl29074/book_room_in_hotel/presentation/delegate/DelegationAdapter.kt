package com.fyrl29074.book_room_in_hotel.presentation.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class DelegationAdapter<T>(
    private val delegatesManager: AdapterDelegatesManager<T>,
    protected open val items: List<T>,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(holder, items, position)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}