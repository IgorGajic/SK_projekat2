package com.example.komponente_spring.dto;

import com.example.komponente_spring.domain.Role;

import java.sql.Date;

public class AdminDto extends UserDto {

    public AdminDto(String username, String password, String email, Date dateOfBirth, String firstName, String lastName) {
        super(username, password, email, dateOfBirth, firstName, lastName, Role.ADMIN, false);
    }
    public AdminDto() {
    }
}
