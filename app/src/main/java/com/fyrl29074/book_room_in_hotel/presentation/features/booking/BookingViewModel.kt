package com.fyrl29074.book_room_in_hotel.presentation.features.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.domain.usecase.GetInfoForBookingUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookingViewModel(
    private val getInfoForBookingUseCase: GetInfoForBookingUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Waiting)
    val state = _state.asStateFlow()

    fun bookRoom() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val info = getInfoForBookingUseCase.execute()
                _state.value = State.Loaded(info)
            } catch (e: Exception) {
                _state.value = State.Error("${e.message}")
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return emailRegex.matches(email)
    }
}