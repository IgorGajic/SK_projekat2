package com.example.komponente_spring.service.impl;

import com.example.komponente_spring.domain.LoyaltyProgram;
import com.example.komponente_spring.dto.LoyaltyProgramDto;
import com.example.komponente_spring.mapper.LoyaltyProgramMapper;
import com.example.komponente_spring.repository.LoyaltyProgramRepository;
import com.example.komponente_spring.service.LoyaltyProgramService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoyaltyProgramServiceImplementation implements LoyaltyProgramService {
    private final LoyaltyProgramRepository loyaltyProgramRepository;
    private final LoyaltyProgramMapper loyaltyProgramMapper;

    public LoyaltyProgramServiceImplementation(LoyaltyProgramRepository loyaltyProgramRepository, LoyaltyProgramMapper loyaltyProgramMapper) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
        this.loyaltyProgramMapper = loyaltyProgramMapper;
    }

    @Override
    public List<LoyaltyProgramDto> findAll() {
        return loyaltyProgramRepository.findAll().stream()
                .map(loyaltyProgram -> loyaltyProgramMapper.getLoyaltyProgramDtoFromLoyaltyProgram(loyaltyProgram))
                .collect(Collectors.toList());
    }

    @Override
    public LoyaltyProgramDto findAllById(Long id) {
        return loyaltyProgramMapper.getLoyaltyProgramDtoFromLoyaltyProgram(loyaltyProgramRepository.findById(id).orElse(null));
    }

    @Override
    public LoyaltyProgramDto create(LoyaltyProgramDto loyaltyProgramDto) {
        LoyaltyProgram loyaltyProgram = loyaltyProgramMapper.getLoyaltyProgramFromLoyaltyProgramDto(loyaltyProgramDto);
        return loyaltyProgramMapper.getLoyaltyProgramDtoFromLoyaltyProgram(loyaltyProgramRepository.save(loyaltyProgram));
    }

    @Override
    public LoyaltyProgramDto update(Long id, LoyaltyProgramDto loyaltyProgramDto) {
        LoyaltyProgram loyaltyProgram = loyaltyProgramMapper.getLoyaltyProgramFromLoyaltyProgramDto(loyaltyProgramDto);
        return loyaltyProgramMapper.getLoyaltyProgramDtoFromLoyaltyProgram(loyaltyProgramRepository.save(loyaltyProgram));
    }

    @Override
    public void delete(Long id) {
        loyaltyProgramRepository.deleteById(id);
    }
}