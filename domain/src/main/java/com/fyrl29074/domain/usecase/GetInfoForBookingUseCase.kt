package com.fyrl29074.domain.usecase

import com.fyrl29074.domain.model.InfoForBooking
import com.fyrl29074.domain.repository.RestRepository

class GetInfoForBookingUseCase(
    private val restRepository: RestRepository,
) {
    suspend fun execute(): InfoForBooking {
        return restRepository.bookRoom()
    }
}