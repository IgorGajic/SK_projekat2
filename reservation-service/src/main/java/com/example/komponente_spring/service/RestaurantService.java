package com.example.komponente_spring.service;

import com.example.komponente_spring.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    List<RestaurantDto> findAll();
    RestaurantDto findById(Long id);
    RestaurantDto create(RestaurantDto restaurantDto);
    RestaurantDto update(Long id, RestaurantDto restaurantDto);
    void delete(Long id);
    RestaurantDto setManager(Long restaurandId, Long managerId);
    RestaurantDto incrementNumberOfAvailableTables(Long id);
    RestaurantDto decrementNumberOfAvailableTables(Long id);
}
