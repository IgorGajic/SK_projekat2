package com.example.komponente_spring.service;

import com.example.komponente_spring.dto.LoyaltyProgramDto;

import java.util.List;

public interface LoyaltyProgramService {
    List<LoyaltyProgramDto> findAll();

    LoyaltyProgramDto findAllById(Long id);

    LoyaltyProgramDto create(LoyaltyProgramDto loyaltyProgramDto);

    LoyaltyProgramDto update(Long id, LoyaltyProgramDto loyaltyProgramDto);

    void delete(Long id);
}
