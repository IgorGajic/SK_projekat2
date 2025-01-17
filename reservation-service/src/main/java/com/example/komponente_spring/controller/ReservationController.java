package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.ReservationDto;
import com.example.komponente_spring.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservationsOfClient/{id}")
    public ResponseEntity<List<ReservationDto>> getReservationsOfClient(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getReservationsOfClient(id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.findAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        return new ResponseEntity<>(reservationService.create(reservationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.findById(id), HttpStatus.OK);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateNotification(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        return new ResponseEntity<>(reservationService.update(id, reservationDto), HttpStatus.OK);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
