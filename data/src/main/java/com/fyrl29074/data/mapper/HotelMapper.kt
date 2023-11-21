package com.fyrl29074.data.mapper

import com.fyrl29074.data.model.AboutTheHotelDto
import com.fyrl29074.data.model.HotelDto
import com.fyrl29074.domain.model.AboutTheHotel
import com.fyrl29074.domain.model.Hotel

object HotelMapper {
    fun map(dto: HotelDto): Hotel {
        return Hotel(
            id = dto.id,
            name = dto.name,
            address = dto.address,
            minimalPrice = dto.minimalPrice,
            priceForIt = dto.priceForIt,
            rating = dto.rating,
            ratingName = dto.ratingName,
            imageUrls = dto.imageUrls,
            aboutTheHotel = this.map(dto.aboutTheHotel),
        )
    }

    private fun map(dto: AboutTheHotelDto): AboutTheHotel {
        return AboutTheHotel(
            description = dto.description,
            peculiarities = dto.peculiarities,
        )
    }
}