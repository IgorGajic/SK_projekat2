package com.example.komponente_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity {
    private Long clientId;
    private Long reservationSlotId; // ID slota povezanog sa ovom rezervacijom
    private Long restaurantId;      // ID restorana

    @Enumerated(EnumType.STRING)
    private Status status;

    public Reservation(Long clientId, Long reservationSlotId, Long restaurantId, Status status) {
        this.clientId = clientId;
        this.reservationSlotId = reservationSlotId;
        this.restaurantId = restaurantId;
        this.status = status;
    }

    public Reservation() {}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
