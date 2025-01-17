package com.example.komponente_spring.service;

import com.example.komponente_spring.dto.ReservationSlotDto;

import java.util.List;

public interface ReservationSlotService {
    List<ReservationSlotDto> findAll();

    ReservationSlotDto findById(Long id);

    ReservationSlotDto create(ReservationSlotDto reservationSlotDto);

    ReservationSlotDto update(Long id, ReservationSlotDto reservationSlotDto);

    void delete(Long id);

    List<ReservationSlotDto> filterAvailableSlots(Long restaurantId);
}
