package com.example.komponente_spring.runner;

import com.example.komponente_spring.domain.CuisineType;
import com.example.komponente_spring.domain.ReservationSlot;
import com.example.komponente_spring.domain.Restaurant;
import com.example.komponente_spring.repository.ReservationSlotRepository;
import com.example.komponente_spring.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;

@Configuration
public class AppRunner {
    private RestaurantRepository restaurantRepository;
    private ReservationSlotRepository reservationSlotRepository;

    public AppRunner(RestaurantRepository restaurantRepository, ReservationSlotRepository reservationSlotRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reservationSlotRepository = reservationSlotRepository;
    }

    @Bean
    CommandLineRunner loadData(){               // ovde punimo bazu podacima, desava se pre pokretanja
        return args -> {
            // Restaurants
            Restaurant restaurant = new Restaurant();
            restaurant.setName("restoran1");
            restaurant.setAddress("adresa1");
            restaurant.setDescription("opis1");
            restaurant.setWorkingHours("radnoVreme1");
            restaurant.setNumberOfTables(10);
            restaurant.setCuisineType(CuisineType.ITALIAN);
            restaurantRepository.save(restaurant);

            Restaurant restaurant2 = new Restaurant();
            restaurant2.setName("restoran2");
            restaurant2.setAddress("adresa2");
            restaurant2.setDescription("opis2");
            restaurant2.setWorkingHours("radnoVreme2");
            restaurant2.setNumberOfTables(20);
            restaurant2.setCuisineType(CuisineType.ITALIAN);
            restaurantRepository.save(restaurant2);

            Restaurant restaurant3 = new Restaurant();
            restaurant3.setName("restoran3");
            restaurant3.setAddress("adresa3");
            restaurant3.setDescription("opis3");
            restaurant3.setWorkingHours("radnoVreme3");
            restaurant3.setNumberOfTables(30);
            restaurant3.setCuisineType(CuisineType.ITALIAN);
            restaurantRepository.save(restaurant3);

            //ReservationSlots
            ReservationSlot reservationSlot = new ReservationSlot();
            reservationSlot.setRestaurantId(1L);
            reservationSlot.setTableNumber(1);
            reservationSlot.setNumberOfSeats(4);
            reservationSlot.setSmokingAllowed(true);
            reservationSlot.setInside(true);
            reservationSlot.setAvailability(true);
            reservationSlot.setDateTime(Date.valueOf(LocalDate.now()));
            reservationSlotRepository.save(reservationSlot);

            ReservationSlot reservationSlot2 = new ReservationSlot();
            reservationSlot2.setRestaurantId(2L);
            reservationSlot2.setTableNumber(2);
            reservationSlot2.setNumberOfSeats(4);
            reservationSlot2.setSmokingAllowed(true);
            reservationSlot2.setInside(true);
            reservationSlot2.setAvailability(true);
            reservationSlot2.setDateTime(Date.valueOf(LocalDate.now()));
            reservationSlotRepository.save(reservationSlot2);

            ReservationSlot reservationSlot3 = new ReservationSlot();
            reservationSlot3.setRestaurantId(3L);
            reservationSlot3.setTableNumber(3);
            reservationSlot3.setNumberOfSeats(4);
            reservationSlot3.setSmokingAllowed(true);
            reservationSlot3.setInside(true);
            reservationSlot3.setAvailability(true);
            reservationSlot3.setDateTime(Date.valueOf(LocalDate.now()));
            reservationSlotRepository.save(reservationSlot3);
        };
    }
}