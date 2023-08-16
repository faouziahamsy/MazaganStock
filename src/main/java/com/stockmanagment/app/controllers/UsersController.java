package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.UsersRequestDto;
import com.stockmanagment.app.dto.UsersResponseDto;
import com.stockmanagment.app.model.Users;
import com.stockmanagment.app.repository.UsersRepository;
import com.stockmanagment.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;
    @CrossOrigin
    @PostMapping
    public ResponseEntity<UsersResponseDto> createUser(@RequestBody UsersRequestDto requestDto)
            throws ChangeSetPersister.NotFoundException {
            UsersResponseDto responseDto = usersService.createUser(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody UsersRequestDto loginRequest) {
        // Perform the login logic here
        System.out.println(loginRequest);
       Users users = usersRepository.findByMatricule(loginRequest.getMatricule());
        if(users.getPassword().equals(loginRequest.getPassword()))
            return ResponseEntity.ok(users);
        return (ResponseEntity<Users>) ResponseEntity.internalServerError();
        // For simplicity, we are just returning a success message.
        // In a real-world scenario, you would validate the credentials and return appropriate responses.

    }
    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDto> updateUser(@PathVariable Long id, @RequestBody UsersRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        UsersResponseDto responseDto = usersService.updateUser(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDto> getUserById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        UsersResponseDto responseDto = usersService.getUserById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDto>> getAllUsers() {
        List<UsersResponseDto> responseDtoList = usersService.getAllUsers();
        return ResponseEntity.ok(responseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
