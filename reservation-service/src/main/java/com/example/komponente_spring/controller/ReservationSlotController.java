package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.ReservationSlotDto;
import com.example.komponente_spring.service.ReservationSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservationSlot")
public class ReservationSlotController {
    private ReservationSlotService reservationSlotService;

    public ReservationSlotController(ReservationSlotService reservationSlotService) {
        this.reservationSlotService = reservationSlotService;
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<ReservationSlotDto>> getAvailableSlots(@RequestParam Long restaurantId) {
        List<ReservationSlotDto> reservationSlotDtos = reservationSlotService.filterAvailableSlots(restaurantId);
        return new ResponseEntity<>(reservationSlotDtos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservationSlotDto>> getAllRestaurants() {
        List<ReservationSlotDto> reservationSlots = reservationSlotService.findAll();
        return new ResponseEntity<>(reservationSlots, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationSlotDto> getOne(@PathVariable Long id){
        return new ResponseEntity<>(reservationSlotService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ReservationSlotDto> create(@RequestBody ReservationSlotDto reservationSlotDto){
        return new ResponseEntity<>(reservationSlotService.create(reservationSlotDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationSlotDto> update(@PathVariable Long id, @RequestBody ReservationSlotDto reservationSlotDto){
        return new ResponseEntity<>(reservationSlotService.update(id, reservationSlotDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reservationSlotService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
