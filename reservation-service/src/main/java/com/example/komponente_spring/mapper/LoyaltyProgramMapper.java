package com.example.komponente_spring.mapper;

import com.example.komponente_spring.domain.LoyaltyProgram;
import com.example.komponente_spring.dto.LoyaltyProgramDto;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyProgramMapper {
    public LoyaltyProgramMapper() {}

    public LoyaltyProgram getLoyaltyProgramFromLoyaltyProgramDto(LoyaltyProgramDto loyaltyProgramDto){
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        loyaltyProgram.setBenefit(loyaltyProgramDto.getBenefit());
        loyaltyProgram.setRequiredReservations(loyaltyProgramDto.getRequiredReservations());
        loyaltyProgram.setRestaurant(new RestaurantMapper().getRestaurantFromRestaurantDto(loyaltyProgramDto.getRestaurant()));
        return loyaltyProgram;
    }

    public LoyaltyProgramDto getLoyaltyProgramDtoFromLoyaltyProgram(LoyaltyProgram loyaltyProgram){
        LoyaltyProgramDto loyaltyProgramDto = new LoyaltyProgramDto();
        loyaltyProgramDto.setBenefit(loyaltyProgram.getBenefit());
        loyaltyProgramDto.setRequiredReservations(loyaltyProgram.getRequiredReservations());
        loyaltyProgramDto.setRestaurant(new RestaurantMapper().getRestaurantDtoFromRestaurant(loyaltyProgram.getRestaurant()));
        return loyaltyProgramDto;
    }
}