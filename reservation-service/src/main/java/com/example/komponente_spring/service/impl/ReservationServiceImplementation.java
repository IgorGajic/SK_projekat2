package com.example.komponente_spring.service.impl;

import com.example.komponente_spring.domain.Reservation;
import com.example.komponente_spring.domain.Restaurant;
import com.example.komponente_spring.domain.Role;
import com.example.komponente_spring.dto.ReservationDto;
import com.example.komponente_spring.dto.ReservationSlotDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.mapper.ReservationMapper;
import com.example.komponente_spring.repository.ReservationRepository;
import com.example.komponente_spring.repository.RestaurantRepository;
import com.example.komponente_spring.service.ReservationService;
import com.example.komponente_spring.service.ReservationSlotService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImplementation implements ReservationService {
    private final ReservationSlotService reservationSlotService;
    private final RestaurantRepository restaurantRepository;
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;
    private RestTemplate userServiceRestTemplate;
    private RestTemplate notificationServiceRestTemplate;


    public ReservationServiceImplementation(ReservationSlotService reservationSlotService, RestaurantRepository restaurantRepository,
                                            ReservationRepository reservationRepository, ReservationMapper reservationMapper,
                                            RestTemplate userServiceRestTemplate, RestTemplate notificationServiceRestTemplate) {
        this.reservationSlotService = reservationSlotService;
        this.restaurantRepository = restaurantRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.notificationServiceRestTemplate = notificationServiceRestTemplate;
    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationRepository.findAll().stream()
                .map(reservation -> reservationMapper.getReservationDtoFromReservation(reservation))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDto findById(Long id) {
        return reservationMapper.getReservationDtoFromReservation(reservationRepository.findById(id).orElse(null));
    }

    @Override
    public ReservationDto create(ReservationDto reservationDto) {
        // Fetch client details using userServiceRestTemplate
        String userUrl = "/users/" + reservationDto.getClientId();
        UserDto user = userServiceRestTemplate.getForObject(userUrl, UserDto.class);

        if(user == null) {
            throw new IllegalArgumentException("Client with ID: " + reservationDto.getClientId() + " does not exist.");
        }
        if(user.getRole() != Role.CLIENT){
            throw new IllegalArgumentException("User with ID: " + reservationDto.getClientId() + " is not a client.");
        }

        // Save the reservation
        Reservation reservation = new Reservation();
        reservation.setClientId(reservationDto.getClientId());

        ReservationSlotDto reservationSlotDto = reservationSlotService.findById(reservationDto.getReservationSlotId());
        if(reservationSlotDto == null) {
            throw new RuntimeException("Reservation slot with ID: " + reservationDto.getReservationSlotId() + " does not exist.");
        }
        reservation.setReservationSlotId(reservationDto.getReservationSlotId());

        Restaurant restaurant = restaurantRepository.findById(reservationSlotDto.getRestaurantId()).orElse(null);
        if(restaurant == null) {
            throw new RuntimeException("Restaurant with ID: " + reservationSlotDto.getRestaurantId() + " does not exist.");
        }

        reservation.setRestaurantId(reservationDto.getRestaurantId());
        reservation.setStatus(reservationDto.getStatus());
        Reservation savedReservation = reservationRepository.save(reservation);

        // Notify client about the successful reservation
//        String notificationUrl = "/notifications";
//        NotificationDto notification = new NotificationDto(user.getEmail(),
//                String.format("Your reservation for slot %s has been successfully created!",
//                        reservationDto.getReservationSlot().toString()));
//        notificationServiceRestTemplate.postForObject(notificationUrl, notification, Void.class);

        userServiceRestTemplate.postForObject(userUrl + "/increment_number_of_reservations", reservationDto.getClientId(), Boolean.class);

        return reservationMapper.getReservationDtoFromReservation(savedReservation);
    }


    @Override
    public ReservationDto update(Long id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null) {
            return null;
        }
        return reservationMapper.getReservationDtoFromReservation(reservationRepository.save(reservationMapper.getReservationFromReservationDto(reservationDto)));
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }


    @Override
    public List<ReservationDto> getReservationsOfClient(Long id) {
        List<ReservationDto> result = new ArrayList<>();
        List<ReservationDto> allReservations = findAll();
        for (ReservationDto reservationDto : allReservations) {
            if (reservationDto.getClientId().equals(id)) {
                result.add(reservationDto);
            }
        }
        return result;
    }
}
