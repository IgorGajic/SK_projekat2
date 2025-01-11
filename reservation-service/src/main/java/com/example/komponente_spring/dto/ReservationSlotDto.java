package com.example.komponente_spring.dto;

import java.sql.Date;

public class ReservationSlotDto {
    private Long restaurantId;
    private int tableNumber;
    private int numberOfSeats;
    private boolean smokingAllowed;
    private boolean isInside;
    private boolean availability;
    private Date dateTime;

    public ReservationSlotDto(Long restaurantId, int tableNumber, int numberOfSeats, boolean smokingAllowed, boolean isInside, boolean availability, Date dateTime) {
        this.restaurantId = restaurantId;
        this.tableNumber = tableNumber;
        this.numberOfSeats = numberOfSeats;
        this.smokingAllowed = smokingAllowed;
        this.isInside = isInside;
        this.availability = availability;
        this.dateTime = dateTime;
    }

    public ReservationSlotDto() {
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public boolean isSmokingAllowed() {
        return smokingAllowed;
    }

    public void setSmokingAllowed(boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean inside) {
        isInside = inside;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
