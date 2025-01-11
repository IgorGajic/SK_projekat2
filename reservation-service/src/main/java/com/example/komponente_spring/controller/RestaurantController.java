package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.RestaurantDto;
import com.example.komponente_spring.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getOne(@PathVariable Long id){
        return new ResponseEntity<>(restaurantService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<RestaurantDto> create(@RequestBody RestaurantDto restaurantDto){
        return new ResponseEntity<>(restaurantService.create(restaurantDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto){
        return new ResponseEntity<>(restaurantService.update(id, restaurantDto), HttpStatus.OK);
    }

    @PostMapping("/set-manager/{restaurantId}/{managerId}")
    public ResponseEntity<RestaurantDto> setManager(@PathVariable Long restaurantId, @PathVariable Long managerId){
        return new ResponseEntity<>(restaurantService.setManager(restaurantId, managerId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        restaurantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
