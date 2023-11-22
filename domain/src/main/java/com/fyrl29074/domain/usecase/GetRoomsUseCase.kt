package com.fyrl29074.domain.usecase

import com.fyrl29074.domain.model.Rooms
import com.fyrl29074.domain.repository.RestRepository

class GetRoomsUseCase(
    private val restRepository: RestRepository,
) {
    suspend fun execute(): Rooms {
        return restRepository.getRooms()
    }
}


