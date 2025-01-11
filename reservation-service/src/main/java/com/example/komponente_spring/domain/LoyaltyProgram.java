package com.example.komponente_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "loyalty_programs")
public class LoyaltyProgram extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    private int requiredReservations;
    private String benefit;

    public LoyaltyProgram(Restaurant restaurant, int requiredReservations, String benefit) {
        this.restaurant = restaurant;
        this.requiredReservations = requiredReservations;
        this.benefit = benefit;
    }

    public LoyaltyProgram() {}

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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
