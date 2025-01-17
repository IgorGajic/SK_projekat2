package com.example.komponente_spring.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.sql.Date;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User{
    private Date hireDate;

    public Manager(String username, String password, String email, Date dateOfBirth, String firstName, String lastName, Date hireDate) {
        super(username, password, email, dateOfBirth, firstName, lastName, Role.MANAGER);
        this.hireDate = hireDate;
    }

    public Manager(Long id, boolean deleted, boolean active, String username, String password, String email, Date dateOfBirth, String firstName, String lastName, Date hireDate) {
        super(id, deleted, active, username, password, email, dateOfBirth, firstName, lastName, Role.MANAGER);
        this.hireDate = hireDate;
    }

    public Manager(Date hireDate) {
        super();
        setRole(Role.MANAGER);
        this.hireDate = hireDate;
    }

    public Manager() {
        super();
        setRole(Role.MANAGER);
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
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
