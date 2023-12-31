package com.fyrl29074.book_room_in_hotel.presentation.features.hotel

import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fyrl29074.book_room_in_hotel.Const
import com.fyrl29074.book_room_in_hotel.R
import com.fyrl29074.book_room_in_hotel.databinding.FragmentHotelBinding
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.book_room_in_hotel.presentation.activity.MainActivity
import com.fyrl29074.book_room_in_hotel.presentation.base.BaseFragment
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate.ImageListItemAdapterDelegate
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate.PeculiarityListItemAdapterDelegate
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.ImagesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.PeculiaritiesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.formatter.ImageFormatter
import com.fyrl29074.book_room_in_hotel.presentation.formatter.PeculiaritiesFormatter
import com.fyrl29074.domain.model.Hotel
import kotlinx.coroutines.launch

class HotelFragment : BaseFragment<FragmentHotelBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHotelBinding
        get() = FragmentHotelBinding::inflate
    private val viewModel: HotelViewModel by viewModel()

    private lateinit var mainActivity: MainActivity

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
        mainActivity = requireActivity() as MainActivity
        mainActivity.showToolbar("Отель")

        with(binding) {
            images.adapter = imagesAdapter
            peculiarities.adapter = peculiaritiesAdapter

            convenienceButton.icon.setImageResource(R.drawable.ic_happy)
            whatIsIncluded.icon.setImageResource(R.drawable.ic_included)
            whatIsNotIncluded.icon.setImageResource(R.drawable.ic_not_included)

            content.visibility = android.view.View.INVISIBLE
        }
    }

    override fun setListeners() = Unit

    override fun initState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Waiting -> {
                        mainActivity.hideToolbar()
                        viewModel.getHotel()
                    }

                    State.Loading -> {
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
                            binding.content.isVisible = true
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
        with(binding) {
            hotelName.text = hotel.name
            hotelAddress.text = hotel.address
            rating.text = getString(R.string.rating, hotel.rating, hotel.ratingName)
            price.text = getString(R.string.price, hotel.minimalPrice)
            pricePer.text = hotel.priceForIt

            convenienceButton.title.text = getString(R.string.convenience)
            convenienceButton.description.text = getString(R.string.the_most_important)

            whatIsIncluded.title.text = getString(R.string.what_is_included)
            whatIsIncluded.description.text = getString(R.string.the_most_important)

            whatIsNotIncluded.title.text = getString(R.string.what_is_not_included)
            whatIsNotIncluded.description.text = getString(R.string.the_most_important)

            toRooms.setOnClickListener {
                val action = HotelFragmentDirections.toRooms(hotel.name)
                findNavController().navigate(action)
            }
        }

        val images = ImageFormatter.format(hotel.imageUrls)
        imagesAdapter.setData(images)
        val peculiarities =
            PeculiaritiesFormatter.format(hotel.aboutTheHotel.peculiarities)
        peculiaritiesAdapter.setData(peculiarities)
    }
}