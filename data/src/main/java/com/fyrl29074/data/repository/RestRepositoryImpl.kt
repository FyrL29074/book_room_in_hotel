package com.fyrl29074.data.repository

import com.fyrl29074.data.RetrofitService
import com.fyrl29074.data.mapper.HotelMapper
import com.fyrl29074.data.model.HotelDto
import com.fyrl29074.domain.model.Hotel
import com.fyrl29074.domain.repository.RestRepository

class RestRepositoryImpl: RestRepository {
    override suspend fun getHotel(): Hotel {
        val response = RetrofitService.api.getHotel()
        if (!response.isSuccessful || response.body() == null) {
            throw Exception(response.message())
        }

        val hotelDto: HotelDto = response.body()!!
        return HotelMapper.map(hotelDto)
    }
}