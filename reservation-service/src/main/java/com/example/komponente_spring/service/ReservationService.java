package com.example.komponente_spring.service;

import com.example.komponente_spring.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> findAll();

    ReservationDto create(ReservationDto reservationDto);

    ReservationDto findById(Long id);

    ReservationDto update(Long id, ReservationDto reservationDto);

    void delete(Long id);

    List<ReservationDto> getReservationsOfClient(Long id);
}
