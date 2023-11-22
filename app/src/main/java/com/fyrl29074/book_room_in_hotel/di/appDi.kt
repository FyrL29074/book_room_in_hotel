package com.fyrl29074.book_room_in_hotel.di

import com.fyrl29074.book_room_in_hotel.presentation.features.hotel.HotelViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    viewModelOf(::HotelViewModel)
}