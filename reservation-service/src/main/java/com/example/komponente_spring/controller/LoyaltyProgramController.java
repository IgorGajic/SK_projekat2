package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.LoyaltyProgramDto;
import com.example.komponente_spring.service.LoyaltyProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loyaltyProgram")
public class LoyaltyProgramController {
    private final LoyaltyProgramService loyaltyProgramService;

    public LoyaltyProgramController(LoyaltyProgramService loyaltyProgramService) {
        this.loyaltyProgramService = loyaltyProgramService;
    }

    @GetMapping
    public ResponseEntity<List<LoyaltyProgramDto>> getAllLoyaltyPrograms() {
        List<LoyaltyProgramDto> loyaltyProgramDtos = loyaltyProgramService.findAll();
        return new ResponseEntity<>(loyaltyProgramDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoyaltyProgramDto> getLoyaltyProgramById(@PathVariable Long id) {
        LoyaltyProgramDto loyaltyProgramDto = loyaltyProgramService.findAllById(id);
        return new ResponseEntity<>(loyaltyProgramDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoyaltyProgramDto> createLoyaltyProgram(@RequestBody LoyaltyProgramDto loyaltyProgramDto) {
        LoyaltyProgramDto createdLoyaltyProgramDto = loyaltyProgramService.create(loyaltyProgramDto);
        return new ResponseEntity<>(createdLoyaltyProgramDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoyaltyProgramDto> updateLoyaltyProgram(@PathVariable Long id, @RequestBody LoyaltyProgramDto loyaltyProgramDto) {
        LoyaltyProgramDto updatedLoyaltyProgramDto = loyaltyProgramService.update(id, loyaltyProgramDto);
        return new ResponseEntity<>(updatedLoyaltyProgramDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoyaltyProgram(@PathVariable Long id) {
        loyaltyProgramService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}