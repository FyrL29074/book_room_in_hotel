package com.fyrl29074.book_room_in_hotel.presentation.features.booking

import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.fyrl29074.book_room_in_hotel.Const
import com.fyrl29074.book_room_in_hotel.R
import com.fyrl29074.book_room_in_hotel.databinding.FragmentBookingBinding
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.book_room_in_hotel.presentation.activity.MainActivity
import com.fyrl29074.book_room_in_hotel.presentation.base.BaseFragment
import com.fyrl29074.book_room_in_hotel.presentation.delegate.AdapterDelegatesManager
import com.fyrl29074.book_room_in_hotel.presentation.delegate.DisplayableItem
import com.fyrl29074.book_room_in_hotel.presentation.delegate.adapterdelegate.TouristListItemAdapterDelegate
import com.fyrl29074.domain.model.InfoForBooking
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class BookingFragment : BaseFragment<FragmentBookingBinding>() {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentBookingBinding
        get() = FragmentBookingBinding::inflate
    private val viewModel: BookingViewModel by viewModel()

    private lateinit var mainActivity: MainActivity

    private val touristsAdapter = TouristsAdapter(
        AdapterDelegatesManager<DisplayableItem>(
            TouristListItemAdapterDelegate(),
        ),
        listOf<DisplayableItem>()
    )

    override fun initUI() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.showToolbar("Бронирование")

        with(binding) {
            infoAboutTourists.tourists.adapter = touristsAdapter

            // here we can add tourists from Room
            touristsAdapter.setData(listOf())
            infoAboutTourists.tourists.isVisible = touristsAdapter.itemCount != 0
        }
    }

    override fun setListeners() {
        with(binding) {
            addTouristSection.addTouristBtn.setOnClickListener {
                infoAboutTourists.tourists.isVisible = true
                touristsAdapter.addEmptyItem()
            }

            infoAboutBuyer.phoneEditText.addTextChangedListener(
                PhoneNumberFormattingTextWatcher()
            )

            infoAboutBuyer.emailEditText.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val enteredEmail = infoAboutBuyer.emailEditText.text.toString()
                    if (viewModel.isValidEmail(enteredEmail)) {
                        infoAboutBuyer.email.error = null
                        infoAboutBuyer.email.setBackgroundResource(R.drawable.bg_rounded_gray)
                    } else {
                        infoAboutBuyer.email.error =
                            getString(R.string.invalid_email_error)
                        infoAboutBuyer.email.setBackgroundColor(
                            requireContext().resources.getColor(
                                R.color.error, requireContext().theme
                            )
                        )
                    }
                }
            }

            pay.setOnClickListener {

            }
        }
    }

    override fun initState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    State.Waiting -> {
                        viewModel.bookRoom()
                    }

                    State.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    is State.Loaded<*> -> {
                        if (state.data is InfoForBooking) {
                            bindLoadedData(state.data)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                Const.WRONG_STATE_ERROR_MESSAGE,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        binding.progressBar.isVisible = false
                    }

                    is State.Error -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    else -> {
                        binding.progressBar.isVisible = false
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

    private fun bindLoadedData(info: InfoForBooking) {
        with(binding) {
            hotelInfo.rating.text = getString(R.string.rating, info.horating, info.ratingName)
            hotelInfo.hotelName.text = info.hotelName
            hotelInfo.hotelAddress.text = info.hotelAddress

            tourInfo.departureFromValue.text = info.departure
            tourInfo.arrivalCountryValue.text = info.arrivalCountry
            tourInfo.datesValue.text =
                getString(R.string.dates_of_tour, info.tourDateStart, info.tourDateStop)
            tourInfo.nightsValue.text = info.numberOfNights.toString()
            tourInfo.hotelValue.text = info.hotelName
            tourInfo.roomValue.text = info.room
            tourInfo.nutritionValue.text = info.nutrition

            expenses.tourValue.text = info.tourPrice.toString()
            expenses.fuelExpensesValue.text = info.fuelCharge.toString()
            expenses.serviceExpensesValue.text = info.serviceCharge.toString()
            val total = info.tourPrice + info.fuelCharge + info.serviceCharge
            expenses.totalValue.text = total.toString()
        }
    }
}