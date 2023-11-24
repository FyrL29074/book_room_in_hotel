package com.fyrl29074.book_room_in_hotel.presentation.features.rooms

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fyrl29074.book_room_in_hotel.Const
import com.fyrl29074.book_room_in_hotel.databinding.FragmentRoomsBinding
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.book_room_in_hotel.presentation.activity.MainActivity
import com.fyrl29074.book_room_in_hotel.presentation.base.BaseFragment
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate.ImageListItemAdapterDelegate
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate.PeculiarityListItemAdapterDelegate
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.ImagesAdapter
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapter.PeculiaritiesAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomsFragment : BaseFragment<FragmentRoomsBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRoomsBinding
        get() = FragmentRoomsBinding::inflate
    private val viewModel: RoomsViewModel by viewModel()
    private val args: RoomsFragmentArgs by navArgs()

    private lateinit var mainActivity: MainActivity

    private val roomAdapter = RoomAdapter(
        imagesAdapter = ImagesAdapter(
            AdapterDelegatesManager<DisplayableItem>(
                ImageListItemAdapterDelegate(),
            ),
            listOf()
        ),
        peculiaritiesAdapter = PeculiaritiesAdapter(
            AdapterDelegatesManager<DisplayableItem>(
                PeculiarityListItemAdapterDelegate(),
            ),
            listOf()
        ),
    ) {
        toBooking()
    }

    override fun initUI() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.showToolbar(args.hotelName)

        binding.rooms.adapter = roomAdapter
    }

    override fun initState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Waiting -> {
                        binding.progressBar.isVisible = false
                        viewModel.getRooms()
                    }

                    State.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is State.Loaded<*> -> {
                        if (state.data is List<*>
                            && state.data.all { it is DisplayableItem.RoomItem }
                        ) {
                            val rooms = state.data as List<DisplayableItem.RoomItem>
                            bindLoadedData(rooms)
                        }

                        binding.progressBar.isVisible = false
                    }

                    is State.Error -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        binding.progressBar.isVisible = false
                    }

                    else -> {
                        Toast.makeText(
                            requireContext(),
                            Const.WRONG_STATE_ERROR_MESSAGE,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun bindLoadedData(rooms: List<DisplayableItem.RoomItem>) {
        roomAdapter.setData(rooms)
    }

    private fun toBooking() {
        val action = RoomsFragmentDirections.toBooking()
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        mainActivity.hideToolbar()
        super.onDestroy()
    }
}