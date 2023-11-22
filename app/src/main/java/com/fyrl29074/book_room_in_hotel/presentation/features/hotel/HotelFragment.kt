package com.fyrl29074.book_room_in_hotel.presentation.features.hotel

import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.fyrl29074.book_room_in_hotel.Const
import com.fyrl29074.book_room_in_hotel.R
import com.fyrl29074.book_room_in_hotel.databinding.FragmentHotelBinding
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.book_room_in_hotel.presentation.base.BaseFragment
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.ImageListItemAdapterDelegate
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.PeculiarityListItemAdapterDelegate
import com.fyrl29074.book_room_in_hotel.presentation.features.hotel.adapter.ImagesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.features.hotel.adapter.PeculiaritiesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.model.formatter.ImageFormatter
import com.fyrl29074.book_room_in_hotel.presentation.model.formatter.PeculiaritiesFormatter
import com.fyrl29074.domain.model.Hotel
import kotlinx.coroutines.launch
import com.google.android.flexbox.FlexboxLayoutManager

class HotelFragment : BaseFragment<FragmentHotelBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHotelBinding
        get() = FragmentHotelBinding::inflate
    private val viewModel: HotelViewModel by viewModel()

    private val imagesAdapter = ImagesAdapter(
        AdapterDelegatesManager<DisplayableItem>(
            ImageListItemAdapterDelegate(),
        ),
        listOf()
    )
    private val peculiaritiesAdapter = PeculiaritiesAdapter(
        AdapterDelegatesManager<DisplayableItem>(
            PeculiarityListItemAdapterDelegate(),
        ),
        listOf()
    )

    override fun initUI() {
        binding.images.adapter = imagesAdapter
        binding.peculiarities.adapter = peculiaritiesAdapter

        val layoutManager = FlexboxLayoutManager(context)
        binding.peculiarities.layoutManager = layoutManager


        binding.convenienceButton.title.text = getString(R.string.convenience)
        binding.convenienceButton.description.text = getString(R.string.the_most_important)

        binding.whatIsIncluded.title.text = getString(R.string.what_is_included)
        binding.whatIsIncluded.description.text = getString(R.string.the_most_important)

        binding.whatIsNotIncluded.title.text = getString(R.string.what_is_not_included)
        binding.whatIsNotIncluded.description.text = getString(R.string.the_most_important)
    }

    override fun initState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Waiting -> {
                        binding.progressBar.isVisible = false
                        viewModel.getHotel()
                    }

                    is State.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is State.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }

                    is State.Loaded<*> -> {
                        if (state.data is Hotel) {
                            val hotel = state.data

                            bindLoadedData(hotel)
                        }

                        binding.progressBar.isVisible = false
                    }

                    else -> {
                        Toast.makeText(requireContext(), Const.WRONG_STATE_ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun bindLoadedData(hotel: Hotel) {
        binding.hotelName.text = hotel.name
        binding.address.text = hotel.address
        binding.rating.text = getString(R.string.rating, hotel.rating, hotel.ratingName)
        binding.price.text = getString(R.string.price, hotel.minimalPrice)
        binding.priceForIt.text = hotel.priceForIt

        val images = ImageFormatter.format(hotel.imageUrls)
        imagesAdapter.setData(images)
        val peculiarities =
            PeculiaritiesFormatter.format(hotel.aboutTheHotel.peculiarities)
        peculiaritiesAdapter.setData(peculiarities)
    }
}