package com.fyrl29074.data.mapper

import com.fyrl29074.data.model.InfoForBookingDto
import com.fyrl29074.domain.model.InfoForBooking

object InfoForBookingMapper {
    fun map(dto: InfoForBookingDto): InfoForBooking {
        return InfoForBooking(
            id = dto.id,
            hotelName = dto.hotelName,
            hotelAddress = dto.hotelAddress,
            horating = dto.horating,
            ratingName = dto.ratingName,
            departure = dto.departure,
            arrivalCountry = dto.arrivalCountry,
            tourDateStart = dto.tourDateStart,
            tourDateStop = dto.tourDateStop,
            numberOfNights = dto.numberOfNights,
            room = dto.room,
            nutrition = dto.nutrition,
            tourPrice = dto.tourPrice,
            fuelCharge = dto.fuelCharge,
            serviceCharge = dto.serviceCharge,
        )
    }
}