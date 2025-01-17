package com.example.komponente_spring.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User{
    private int numberOfReservations;

    public Client(String username, String password, String email, Date dateOfBirth, String firstName, String lastName, int numberOfReservations) {
        super(username, password, email, dateOfBirth, firstName, lastName, Role.CLIENT);
        this.numberOfReservations = numberOfReservations;
    }

    public Client(Long id, boolean deleted, boolean active, String username, String password, String email, Date dateOfBirth, String firstName, String lastName, int numberOfReservations) {
        super(id, deleted, active, username, password, email, dateOfBirth, firstName, lastName, Role.CLIENT);
        this.numberOfReservations = numberOfReservations;
    }

    public Client(int numberOfReservations) {
        super();
        setRole(Role.CLIENT);
        this.numberOfReservations = numberOfReservations;
    }

    public Client() {
        super();
        setRole(Role.CLIENT);
    }


    public int getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public Date getDateOfBirth() {
        return super.getDateOfBirth();
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        super.setDateOfBirth(dateOfBirth);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }
}
