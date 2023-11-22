package com.fyrl29074.book_room_in_hotel.presentation.features.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.domain.usecase.GetHotelUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HotelViewModel(
    private val getHotelUseCase: GetHotelUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Waiting)
    val state = _state.asStateFlow()

    fun getHotel() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val hotel = getHotelUseCase.execute()
                _state.value = State.Loaded(hotel)
            } catch (e: Exception) {
                _state.value = State.Error(e.message?: "Unknown error")
            }
        }
    }
}