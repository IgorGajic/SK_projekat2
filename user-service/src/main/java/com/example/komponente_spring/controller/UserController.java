package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.ClientDto;
import com.example.komponente_spring.dto.LoginDto;
import com.example.komponente_spring.dto.ManagerDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getOne(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/users/{id}/increment_number_of_reservations")
    public ResponseEntity<Boolean> incrementNumberOfReservations(@PathVariable Long id){
        return new ResponseEntity<>(userService.incrementNumberOfReservations(id), HttpStatus.OK);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

    @PostMapping("/registerClient")
    public ResponseEntity<String> registerUser(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(userService.registerClient(clientDto), HttpStatus.CREATED);
    }

    @PostMapping("/registerManager")
    public ResponseEntity<String> registerManager(@RequestBody ManagerDto managerDto){
        return new ResponseEntity<>(userService.registerManager(managerDto), HttpStatus.CREATED);
    }

   // @CheckSecurity(roles = {"ROLE_ADMIN"})
    @PostMapping("/block")
    public ResponseEntity<String> blockUser(@RequestParam String username, @RequestParam String email) {
        userService.blockUser(username, email);
        return new ResponseEntity<>("Korisnik je blokiran", HttpStatus.OK);
    }

    @PostMapping("/unblock")
    public ResponseEntity<String> unblockUser(@RequestParam String username, @RequestParam String email) {
        userService.unblockUser(username, email);
        return new ResponseEntity<>("Korisnik je odblokiran", HttpStatus.OK);
    }
}
