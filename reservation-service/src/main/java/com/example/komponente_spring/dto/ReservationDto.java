package com.example.komponente_spring.dto;

import com.example.komponente_spring.domain.Status;

public class ReservationDto {
    private Long clientId;
    private Long reservationSlotId; // ID slota povezanog sa ovom rezervacijom
    private Long restaurantId;
    private Status status;

    public ReservationDto() {
    }

    public ReservationDto(Long clientId, Long reservationSlotId, Long restaurantId, Status status) {
        this.clientId = clientId;
        this.reservationSlotId = reservationSlotId;
        this.restaurantId = restaurantId;
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getReservationSlotId() {
        return reservationSlotId;
    }

    public void setReservationSlotId(Long reservationSlotId) {
        this.reservationSlotId = reservationSlotId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}