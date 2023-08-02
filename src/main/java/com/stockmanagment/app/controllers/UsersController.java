package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.UsersDto;
import com.stockmanagment.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<UsersDto> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsersDto getUsersById(@PathVariable Long id) {
        return usersService.getUsersById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersDto createUser(@RequestBody UsersDto usersDto) {
        return usersService.createUser(usersDto);
    }

    @PutMapping("/{id}")
    public UsersDto updateUser(@PathVariable Long id, @RequestBody UsersDto usersDto) {
        return usersService.updateUser(id, usersDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
}
