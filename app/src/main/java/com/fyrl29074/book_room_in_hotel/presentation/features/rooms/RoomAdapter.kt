package com.fyrl29074.book_room_in_hotel.presentation.features.rooms

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyrl29074.book_room_in_hotel.databinding.ItemRoomBinding
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.ImagesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.PeculiaritiesAdapter

class RoomAdapter(
    private val imagesAdapter: ImagesAdapter,
    private val peculiaritiesAdapter: PeculiaritiesAdapter,
    private val toBooking: () -> Unit,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var rooms = listOf<DisplayableItem.RoomItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DisplayableItem.RoomItem>) {
        this.rooms = data
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    override fun getItemCount(): Int = rooms.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RoomViewHolder).bind(rooms[position])
    }

    inner class RoomViewHolder(private val binding: ItemRoomBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(room: DisplayableItem.RoomItem) {
            with(binding) {
                images.adapter = imagesAdapter
                imagesAdapter.setData(room.imageUrls)

                name.text = room.name

                peculiarities.adapter = peculiaritiesAdapter
                peculiaritiesAdapter.setData(room.peculiarities)

                price.text = room.price.toString()
                pricePer.text = room.pricePer
                chooseRoom.setOnClickListener {
                    toBooking()
                }
            }
        }
    }

}