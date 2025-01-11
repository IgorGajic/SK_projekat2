package com.example.komponente_spring.service.impl;

import com.example.komponente_spring.domain.Client;
import com.example.komponente_spring.domain.Manager;
import com.example.komponente_spring.domain.Role;
import com.example.komponente_spring.domain.User;
import com.example.komponente_spring.dto.ClientDto;
import com.example.komponente_spring.dto.LoginDto;
import com.example.komponente_spring.dto.ManagerDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.mapper.UserMapper;
import com.example.komponente_spring.repository.UserRepository;
import com.example.komponente_spring.security.service.TokenService;
import com.example.komponente_spring.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private TokenService tokenService;

    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper, TokenService tokenService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword()).orElse(null);
        if(user != null){
            if(user.isBlocked())
                return "User is blocked";
            Claims claims = Jwts.claims();
            claims.put("role", user.getRole());
            claims.put("username", user.getUsername());
            claims.put("id", user.getId());
            return tokenService.generate(claims);

        }
        return "User does not exist";
    }

    @Override
    public String registerClient(ClientDto clientDto){
        Client regesiteredClient = (Client) userMapper.getUserDomainFromUserDto(clientDto);

        Client savedClient = userRepository.save(regesiteredClient);

        Claims claims = Jwts.claims();
        claims.put("role", savedClient.getRole());
        claims.put("username", savedClient.getUsername());
        claims.put("id", savedClient.getId());
        return tokenService.generate(claims);
    }

    @Override
    public String registerManager(ManagerDto managerDto){
        Manager regesiteredManager = (Manager) userMapper.getUserDomainFromUserDto(managerDto);

        Manager savedManager = userRepository.save(regesiteredManager);

        Claims claims = Jwts.claims();
        claims.put("role", savedManager.getRole());
        claims.put("username", savedManager.getUsername());
        claims.put("id", savedManager.getId());
        return tokenService.generate(claims);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> userMapper.getUserDtoFromUserDomain(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.getUserDtoFromUserDomain(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserDto create(UserDto userDto) {
        return userMapper.getUserDtoFromUserDomain(userRepository.save(userMapper.getUserDomainFromUserDto(userDto)));
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        return userMapper.getUserDtoFromUserDomain(userRepository.save(userMapper.getUserDomainFromUserDto(userDto)));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void blockUser(String username, String email) {
        User user = userRepository.findByUsernameAndEmail(username, email).orElse(null);
        if (user != null) {
            user.setBlocked(true);
            userRepository.save(user);
        }
    }

    @Override
    public void unblockUser(String username, String email) {
        User user = userRepository.findByUsernameAndEmail(username, email).orElse(null);
        if (user != null) {
            user.setBlocked(false);
            userRepository.save(user);
        }
    }

    @Override
    public Boolean incrementNumberOfReservations(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null && user instanceof Client) {
            ((Client) user).setNumberOfReservations(((Client) user).getNumberOfReservations() + 1);
            userRepository.save(user);
            return true;
        }
        return false;
    }

}