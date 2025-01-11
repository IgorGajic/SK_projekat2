package com.example.komponente_spring.dto;

public class LoyaltyProgramDto {
    private RestaurantDto restaurant;
    private int requiredReservations;
    private String benefit;

    public LoyaltyProgramDto() {}

    public LoyaltyProgramDto(RestaurantDto restaurant, int requiredReservations, String benefit) {
        this.restaurant = restaurant;
        this.requiredReservations = requiredReservations;
        this.benefit = benefit;
    }

    public RestaurantDto getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDto restaurant) {
        this.restaurant = restaurant;
    }

    public int getRequiredReservations() {
        return requiredReservations;
    }

    public void setRequiredReservations(int requiredReservations) {
        this.requiredReservations = requiredReservations;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }
}