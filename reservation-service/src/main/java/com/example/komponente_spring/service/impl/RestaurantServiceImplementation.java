package com.example.komponente_spring.service.impl;

import com.example.komponente_spring.domain.Restaurant;
import com.example.komponente_spring.domain.Role;
import com.example.komponente_spring.dto.RestaurantDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.mapper.RestaurantMapper;
import com.example.komponente_spring.repository.RestaurantRepository;
import com.example.komponente_spring.service.RestaurantService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImplementation implements RestaurantService {
    private final RestTemplate userServiceRestTemplate;
    private RestaurantRepository restaurantRepository;
    private RestaurantMapper restaurantMapper;

    public RestaurantServiceImplementation(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper, RestTemplate userServiceRestTemplate) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
    }

    @Override
    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> restaurantMapper.getRestaurantDtoFromRestaurant(restaurant))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDto findById(Long id) {
        return restaurantMapper.getRestaurantDtoFromRestaurant(restaurantRepository.findById(id).orElse(null));
    }

    @Override
    public RestaurantDto incrementNumberOfAvailableTables(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant == null) {
            return null;
        }
        restaurant.setNumberOfAvailableTables(restaurant.getNumberOfAvailableTables() + 1);
        return restaurantMapper.getRestaurantDtoFromRestaurant(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto decrementNumberOfAvailableTables(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant == null) {
            return null;
        }
        restaurant.setNumberOfAvailableTables(restaurant.getNumberOfAvailableTables() - 1);
        return restaurantMapper.getRestaurantDtoFromRestaurant(restaurantRepository.save(restaurant));
    }

    @Override
    public RestaurantDto create(RestaurantDto restaurantDto) {
        return restaurantMapper.getRestaurantDtoFromRestaurant(restaurantRepository.save(restaurantMapper.getRestaurantFromRestaurantDto(restaurantDto)));
    }

    @Override
    public RestaurantDto update(Long id, RestaurantDto restaurantDto) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        UserDto userDto = userServiceRestTemplate.getForObject("/users/" + restaurantDto.getManagerId(), UserDto.class);
        if (restaurant == null || userDto == null) {
            return null;
        }
        if(userDto.getRole() != Role.MANAGER){
            throw new RuntimeException("User with ID: " + restaurantDto.getManagerId() + " is not a manager.");
        }
        return restaurantMapper.getRestaurantDtoFromRestaurant(restaurantRepository.save(restaurantMapper.getRestaurantFromRestaurantDto(restaurantDto)));
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public RestaurantDto setManager(Long restaurandId, Long managerId) {
        Restaurant restaurant = restaurantRepository.findById(restaurandId).orElseThrow();
        restaurant.setManagerId(managerId);
        return restaurantMapper.getRestaurantDtoFromRestaurant(restaurantRepository.save(restaurant));
    }


    public RestaurantRepository getRestaurantRepository() {
        return restaurantRepository;
    }

    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantMapper getRestaurantMapper() {
        return restaurantMapper;
    }

    public void setRestaurantMapper(RestaurantMapper restaurantMapper) {
        this.restaurantMapper = restaurantMapper;
    }
}
