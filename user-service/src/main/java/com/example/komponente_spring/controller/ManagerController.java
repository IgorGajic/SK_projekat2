package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.ManagerDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private UserService userService;

    public ManagerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<ManagerDto>> getAll(){
        List<ManagerDto> managerDtos = new ArrayList<>();
        for (UserDto userDto : userService.findAll()) {
            if(userDto instanceof ManagerDto)
                managerDtos.add((ManagerDto) userDto);
        }
        return new ResponseEntity<>(managerDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ManagerDto> getOne(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        if(userDto instanceof ManagerDto)
            return new ResponseEntity<>((ManagerDto) userService.findById(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<ManagerDto> create(@RequestBody ManagerDto managerDto){
        return new ResponseEntity<>((ManagerDto) userService.create(managerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerDto> update(@PathVariable Long id, @RequestBody ManagerDto managerDto){
        return new ResponseEntity<>((ManagerDto) userService.update(id, managerDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
