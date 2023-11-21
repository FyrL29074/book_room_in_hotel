package com.fyrl29074.book_room_in_hotel.di

import com.fyrl29074.data.repository.RestRepositoryImpl
import com.fyrl29074.domain.repository.RestRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    singleOf(::RestRepositoryImpl) bind RestRepository::class
}