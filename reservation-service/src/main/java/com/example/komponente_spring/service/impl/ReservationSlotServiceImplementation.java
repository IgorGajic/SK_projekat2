package com.example.komponente_spring.service.impl;

import com.example.komponente_spring.domain.ReservationSlot;
import com.example.komponente_spring.domain.Role;
import com.example.komponente_spring.dto.ReservationSlotDto;
import com.example.komponente_spring.dto.RestaurantDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.mapper.ReservationSlotMapper;
import com.example.komponente_spring.repository.ReservationSlotRepository;
import com.example.komponente_spring.service.ReservationSlotService;
import com.example.komponente_spring.service.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationSlotServiceImplementation implements ReservationSlotService {
    private final RestTemplate userServiceRestTemplate;
    private final RestaurantService restaurantService;
    private ReservationSlotRepository reservationSlotRepository;
    private ReservationSlotMapper reservationSlotMapper;

    public ReservationSlotServiceImplementation(ReservationSlotRepository reservationSlotRepository, ReservationSlotMapper reservationSlotMapper, RestTemplate userServiceRestTemplate, RestaurantService restaurantService) {
        this.reservationSlotRepository = reservationSlotRepository;
        this.reservationSlotMapper = reservationSlotMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.restaurantService = restaurantService;
    }

    @Override
    public List<ReservationSlotDto> findAll() {
        return reservationSlotRepository.findAll().stream()
                .map(reservationSlot -> reservationSlotMapper.getReservationSlotDtoFromReservationSlot(reservationSlot))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationSlotDto findById(Long id) {
        return reservationSlotMapper.getReservationSlotDtoFromReservationSlot(reservationSlotRepository.findById(id).orElse(null));
    }

    @Override
    public ReservationSlotDto create(ReservationSlotDto reservationSlotDto) {
        RestaurantDto restaurantDto = restaurantService.findById(reservationSlotDto.getRestaurantId());
        if (restaurantDto == null)
            return null;
        restaurantDto.decrementNumberOfAvailableTables();
        restaurantService.decrementNumberOfAvailableTables(reservationSlotDto.getRestaurantId());

        return reservationSlotMapper.getReservationSlotDtoFromReservationSlot(reservationSlotRepository.save(reservationSlotMapper.getReservationSlotFromReservationSlotDto(reservationSlotDto)));
    }

    @Override
    public ReservationSlotDto update(Long id, ReservationSlotDto reservationSlotDto) {
        ReservationSlot reservationSlot = reservationSlotRepository.findById(id).orElse(null);
        RestaurantDto restaurantDto = restaurantService.findById(reservationSlotDto.getRestaurantId());
        UserDto userDto = userServiceRestTemplate.getForObject("/users/" + restaurantDto.getManagerId(), UserDto.class);

        if (reservationSlot == null || userDto == null) {
            return null;
        }
        if(userDto.getRole() != Role.MANAGER){
            throw new RuntimeException("User with ID: " + restaurantDto.getManagerId() + " is not a manager.");
        }
        return reservationSlotMapper.getReservationSlotDtoFromReservationSlot(reservationSlotRepository.save(reservationSlotMapper.getReservationSlotFromReservationSlotDto(reservationSlotDto)));
    }

    @Override
    public void delete(Long id) {
        ReservationSlotDto reservationSlotDto = findById(id);
        if (reservationSlotDto == null) {
            return;
        }
        RestaurantDto restaurantDto = restaurantService.findById(reservationSlotDto.getRestaurantId());
        if(restaurantDto == null)
            return;
        restaurantDto.incrementNumberOfAvailableTables();
        restaurantService.incrementNumberOfAvailableTables(reservationSlotDto.getRestaurantId());

        reservationSlotRepository.deleteById(id);
    }

    @Override
    public List<ReservationSlotDto> filterAvailableSlots(Long restaurantId) {
        List<ReservationSlot> availableSlots = reservationSlotRepository.findAll();
        List<ReservationSlotDto> filteredSlots = new ArrayList<>();

        for (ReservationSlot slot : availableSlots) {
            if (slot.getRestaurantId().equals(restaurantId)){
                filteredSlots.add(reservationSlotMapper.getReservationSlotDtoFromReservationSlot(slot));
            }
        }

        return filteredSlots;
    }

}
