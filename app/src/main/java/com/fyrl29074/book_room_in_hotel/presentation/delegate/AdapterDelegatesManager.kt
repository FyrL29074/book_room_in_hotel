package com.fyrl29074.book_room_in_hotel.presentation.delegate

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate.AdapterDelegate

class AdapterDelegatesManager<T>(vararg delegates: AdapterDelegate<T>) {
    private val delegates: SparseArrayCompat<AdapterDelegate<T>> = SparseArrayCompat()

    init {
        for (element in delegates) {
            addDelegate(element)
        }
    }

    private fun addDelegate(delegate: AdapterDelegate<T>) {
        var viewType = delegates.size()
        while (delegates[viewType] != null) {
            viewType++
        }
        delegates.put(viewType, delegate)
    }

    fun getItemViewType(items: List<T>, position: Int): Int {
        val delegatesCount = delegates.size()
        for (i in 0 until delegatesCount) {
            val delegate: AdapterDelegate<T> = delegates.valueAt(i)
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i)
            }
        }

        throw IllegalArgumentException("No delegate found for position $position")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate: AdapterDelegate<T> = delegates[viewType]
            ?: throw IllegalArgumentException("No delegate found for view type $viewType")

        return delegate.onCreateViewHolder(parent)
    }

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, items: List<T>, position: Int) {
        val delegate: AdapterDelegate<T> = delegates[holder.itemViewType]
            ?: throw IllegalArgumentException("No delegate found for view type ${holder.itemViewType}")

        delegate.onBindViewHolder(holder, items, position)
    }
}