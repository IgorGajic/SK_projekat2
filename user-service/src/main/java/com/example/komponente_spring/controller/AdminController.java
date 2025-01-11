package com.example.komponente_spring.controller;

import com.example.komponente_spring.dto.AdminDto;
import com.example.komponente_spring.dto.UserDto;
import com.example.komponente_spring.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<AdminDto>> getAll(){
        List<AdminDto> adminDtos = new ArrayList<>();
        for (UserDto userDto : userService.findAll()) {
            if(userDto instanceof AdminDto)
                adminDtos.add((AdminDto) userDto);
        }
        return new ResponseEntity<>(adminDtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getOne(@PathVariable Long id){
        UserDto userDto = userService.findById(id);
        if(userDto instanceof AdminDto)
            return new ResponseEntity<>((AdminDto) userService.findById(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<AdminDto> create(@RequestBody AdminDto adminDto){
        return new ResponseEntity<>((AdminDto)userService.create(adminDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> update(@PathVariable Long id, @RequestBody AdminDto adminDto){
        return new ResponseEntity<>((AdminDto) userService.update(id, adminDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
