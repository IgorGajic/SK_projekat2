package com.example.komponente_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity {
    private String name;
    private String address;
    private String description;
    private int numberOfTables;
    private String workingHours;
    private Long managerId;
    private int numberOfAvailableTables;

    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;

    public Restaurant() {}

    public Restaurant(String name, String address, String description, int numberOfTables, String workingHours, Long managerId, CuisineType cuisineType) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.numberOfTables = numberOfTables;
        this.workingHours = workingHours;
        this.managerId = managerId;
        this.numberOfAvailableTables = numberOfTables;
        this.cuisineType = cuisineType;
    }

    public Restaurant(Long id, boolean deleted, boolean active, String name, String address, String description, int numberOfTables, String workingHours, CuisineType cuisineType, Long managerId) {
        super(id, deleted, active);
        this.name = name;
        this.address = address;
        this.description = description;
        this.numberOfTables = numberOfTables;
        this.workingHours = workingHours;
        this.cuisineType = cuisineType;
        this.managerId = managerId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;

    }

    public int getNumberOfAvailableTables() {
        return numberOfAvailableTables;
    }

    public void incrementNumberOfAvailableTables() {
        if(this.numberOfAvailableTables == this.numberOfTables)
            return;
        this.numberOfAvailableTables++;
    }

    public void decrementNumberOfAvailableTables() {
        if(this.numberOfAvailableTables == 0)
            return;
        this.numberOfAvailableTables--;
    }

    public void setNumberOfAvailableTables(int numberOfAvailableTables) {
        this.numberOfAvailableTables = numberOfAvailableTables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        int numberOfTakenSeats = this.numberOfTables - this.numberOfAvailableTables;
        if(numberOfTables < numberOfTakenSeats)
            return;
        this.numberOfTables = numberOfTables;
        this.numberOfAvailableTables = numberOfTables - numberOfTakenSeats;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }
}
