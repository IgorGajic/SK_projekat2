package com.example.komponente_spring.service;

import com.example.komponente_spring.dto.ClientDto;
import com.example.komponente_spring.dto.LoginDto;
import com.example.komponente_spring.dto.ManagerDto;
import com.example.komponente_spring.dto.UserDto;

import java.util.List;

public interface UserService {
    String login(LoginDto loginDto);


    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto create(UserDto userDto);
    UserDto update(Long id, UserDto userDto);
    void delete(Long id);

    String registerClient(ClientDto clientDto);
    String registerManager(ManagerDto managerDto);

    void blockUser(String username, String email);
    void unblockUser(String username, String email);

    Boolean incrementNumberOfReservations(Long id);
}