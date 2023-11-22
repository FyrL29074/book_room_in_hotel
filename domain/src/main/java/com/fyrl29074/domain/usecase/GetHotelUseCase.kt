package com.fyrl29074.domain.usecase

import com.fyrl29074.domain.model.Hotel
import com.fyrl29074.domain.repository.RestRepository

class GetHotelUseCase(
    private val restRepository: RestRepository,
) {
    suspend fun execute(): Hotel {
        return restRepository.getHotel()
    }
}