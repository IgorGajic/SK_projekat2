package com.example.komponente_spring.mapper;

import com.example.komponente_spring.domain.ReservationSlot;
import com.example.komponente_spring.dto.ReservationSlotDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationSlotMapper {
    public ReservationSlotMapper() {}

    public ReservationSlot getReservationSlotFromReservationSlotDto(ReservationSlotDto reservationSlotDto){
       ReservationSlot reservationSlot = new ReservationSlot();
       reservationSlot.setRestaurantId(reservationSlotDto.getRestaurantId());
       reservationSlot.setTableNumber(reservationSlotDto.getTableNumber());
       reservationSlot.setNumberOfSeats(reservationSlotDto.getNumberOfSeats());
       reservationSlot.setSmokingAllowed(reservationSlotDto.isSmokingAllowed());
       reservationSlot.setInside(reservationSlotDto.isInside());
       reservationSlot.setAvailability(reservationSlotDto.isAvailability());
       reservationSlot.setDateTime(reservationSlotDto.getDateTime());
       return reservationSlot;
    }
    public ReservationSlotDto getReservationSlotDtoFromReservationSlot(ReservationSlot reservationSlot){
        ReservationSlotDto reservationSlotDto = new ReservationSlotDto();
        reservationSlotDto.setRestaurantId(reservationSlot.getRestaurantId());
        reservationSlotDto.setNumberOfSeats(reservationSlot.getNumberOfSeats());
        reservationSlotDto.setTableNumber(reservationSlot.getTableNumber());
        reservationSlotDto.setSmokingAllowed(reservationSlot.isSmokingAllowed());
        reservationSlotDto.setInside(reservationSlot.isInside());
        reservationSlotDto.setAvailability(reservationSlot.isAvailability());
        reservationSlotDto.setDateTime(reservationSlot.getDateTime());
        return reservationSlotDto;
    }
}
