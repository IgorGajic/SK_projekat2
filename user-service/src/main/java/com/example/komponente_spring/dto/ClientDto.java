package com.example.komponente_spring.dto;

import com.example.komponente_spring.domain.Role;

import java.sql.Date;

public class ClientDto extends UserDto {
    private int numberOfReservations;

    public ClientDto(String username, String password, String email, Date dateOfBirth, String firstName, String lastName, int numberOfReservations) {
        super(username, password, email, dateOfBirth, firstName, lastName, Role.CLIENT, false);
        this.numberOfReservations = numberOfReservations;
    }

    public ClientDto() {}

    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }
}
