package com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.fyrl29074.book_room_in_hotel.R
import com.fyrl29074.book_room_in_hotel.databinding.ItemTouristBinding
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem

class TouristListItemAdapterDelegate: AdapterDelegate<DisplayableItem> {
    override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
        return items[position] is DisplayableItem.TouristItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemTouristBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TouristViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        items: List<DisplayableItem>,
        position: Int
    ) {
        (holder as TouristViewHolder).bind(items[position] as DisplayableItem.TouristItem)
    }

    inner class TouristViewHolder(private val binding: ItemTouristBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DisplayableItem.TouristItem) {
            with(binding) {
                touristCounter.text = binding.root.context.getString(R.string.tourist_counter, item.id + 1)
                nameEditText.setText(item.name)
                surnameEditText.setText(item.surname)
                dateOfBirthEditText.setText(item.dateOfBirth)
                citizenshipEditText.setText(item.citizenship)
                foreignPassportNumberEditText.setText(item.foreignPassportNumber)
                foreignPassportExpiryDateEditText.setText(item.foreignPassportExpiryDate)

                hideOpenTourist.setOnClickListener {
                    if (infoContainer.isVisible) {
                        infoContainer.isVisible = false

                        hideOpenTourist.setImageDrawable(
                            AppCompatResources.getDrawable(
                                binding.root.context,
                                R.drawable.ic_arrow_down_blue
                            )
                        )
                    } else {
                        infoContainer.isVisible = true
                        hideOpenTourist.setImageDrawable(
                            AppCompatResources.getDrawable(
                                binding.root.context,
                                R.drawable.ic_arrow_up_blue,
                            )
                        )
                    }
                }
            }
        }
    }
}