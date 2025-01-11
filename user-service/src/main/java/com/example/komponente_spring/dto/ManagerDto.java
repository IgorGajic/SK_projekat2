package com.example.komponente_spring.dto;

import com.example.komponente_spring.domain.Role;

import java.sql.Date;

public class ManagerDto extends UserDto{
    private Date hireDate;

    public ManagerDto(String username, String password, String email, Date dateOfBirth, String firstName, String lastName, Date hireDate) {
        super(username, password, email, dateOfBirth, firstName, lastName, Role.MANAGER, false);
        this.hireDate = hireDate;
    }

    public ManagerDto() {}

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
