package com.fyrl29074.book_room_in_hotel.presentation

sealed class State {
    data object Waiting: State()
    data object Loading : State()
    data object Success : State()
    data class Loaded<T>(val data: T) : State()
    data class Error(val message: String) : State()
}
