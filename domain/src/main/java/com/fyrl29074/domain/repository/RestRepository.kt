package com.fyrl29074.domain.repository

import com.fyrl29074.domain.model.Hotel
import com.fyrl29074.domain.model.InfoForBooking
import com.fyrl29074.domain.model.Rooms

interface RestRepository {
    suspend fun getHotel(): Hotel
    suspend fun getRooms(): Rooms
    suspend fun bookRoom(): InfoForBooking
}