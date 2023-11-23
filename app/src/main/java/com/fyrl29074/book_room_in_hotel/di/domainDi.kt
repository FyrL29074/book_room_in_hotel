package com.fyrl29074.book_room_in_hotel.di

import com.fyrl29074.domain.usecase.GetHotelUseCase
import com.fyrl29074.domain.usecase.GetRoomsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {

    factoryOf(::GetHotelUseCase)
    factoryOf(::GetRoomsUseCase)
}