package com.fyrl29074.book_room_in_hotel.presentation.features.rooms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fyrl29074.book_room_in_hotel.presentation.State
import com.fyrl29074.book_room_in_hotel.presentation.model.formatter.RoomFormatter
import com.fyrl29074.domain.usecase.GetRoomsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoomsViewModel(
    private val getRoomsUseCase: GetRoomsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Waiting)
    val state = _state.asStateFlow()

    fun getRooms() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val rooms = RoomFormatter.format(getRoomsUseCase.execute())
                _state.value = State.Loaded(rooms)
            } catch (e: Exception) {
                _state.value = State.Error("${e.message}")
            }
        }
    }
}