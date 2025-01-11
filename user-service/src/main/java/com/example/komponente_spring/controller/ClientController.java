package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.ClientDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public ResponseEntity<List<ClientDto>> getAll(){
        List<ClientDto> clientDtos = new ArrayList<>();
        for (UserDto userDto : userService.findAll()) {
            if(userDto instanceof ClientDto)
                clientDtos.add((ClientDto) userDto);
        }
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getOne(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        if(userDto instanceof ClientDto)
            return new ResponseEntity<>((ClientDto) userDto, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>((ClientDto) userService.create(clientDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody ClientDto clientDto){
        return new ResponseEntity<>((ClientDto) userService.update(id, clientDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
