package com.example.komponente_spring.mapper;

import com.example.komponente_spring.domain.Restaurant;
import com.example.komponente_spring.dto.RestaurantDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public RestaurantMapper() {}

    public Restaurant getRestaurantFromRestaurantDto(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setName(restaurantDto.getName());
        restaurant.setNumberOfTables(restaurantDto.getNumberOfTables());
        restaurant.setWorkingHours(restaurantDto.getWorkingHours());
        restaurant.setCuisineType(restaurantDto.getCuisineType());
        restaurant.setManagerId(restaurantDto.getManagerId());
        return restaurant;
    }

    public RestaurantDto getRestaurantDtoFromRestaurant(Restaurant restaurant){
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setAddress(restaurant.getAddress());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setNumberOfTables(restaurant.getNumberOfTables());
        restaurantDto.setWorkingHours(restaurant.getWorkingHours());
        restaurantDto.setCuisineType(restaurant.getCuisineType());
        restaurantDto.setManagerId(restaurant.getManagerId());
        return restaurantDto;
    }
}

