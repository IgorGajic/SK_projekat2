package com.example.komponente_spring.mapper;

import com.example.komponente_spring.domain.Reservation;
import com.example.komponente_spring.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationMapper() {}

    public Reservation getReservationFromReservationDto(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setStatus(reservationDto.getStatus());
        reservation.setClientId(reservationDto.getClientId());
        reservation.setReservationSlotId(reservationDto.getReservationSlotId());
        reservation.setRestaurantId(reservationDto.getRestaurantId());
        return reservation;
    }

    public ReservationDto getReservationDtoFromReservation(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setStatus(reservation.getStatus());
        reservationDto.setClientId(reservation.getClientId());
        reservationDto.setReservationSlotId(reservation.getReservationSlotId());
        reservationDto.setRestaurantId(reservation.getRestaurantId());
        return reservationDto;
    }
}
