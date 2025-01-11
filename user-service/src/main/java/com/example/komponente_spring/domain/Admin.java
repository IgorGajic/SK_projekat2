package com.example.komponente_spring.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin(String username, String password, String email, Date dateOfBirth, String firstName, String lastName) {
        super(username, password, email, dateOfBirth, firstName, lastName, Role.ADMIN);
    }

    public Admin(Long id, boolean deleted, boolean active, String username, String password, String email, Date dateOfBirth, String firstName, String lastName) {
        super(id, deleted, active, username, password, email, dateOfBirth, firstName, lastName, Role.ADMIN);
    }

    public Admin() {
        setRole(Role.ADMIN);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public Date getDateOfBirth() {
        return super.getDateOfBirth();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setDateOfBirth(Date dateOfBirth) {
        super.setDateOfBirth(dateOfBirth);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }
}
