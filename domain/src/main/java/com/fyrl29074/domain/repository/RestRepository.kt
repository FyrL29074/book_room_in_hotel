package com.fyrl29074.domain.repository

import com.fyrl29074.domain.model.Hotel

interface RestRepository {
    suspend fun getHotel(): Hotel
}