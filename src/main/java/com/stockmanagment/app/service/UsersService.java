package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.UsersDto;
import com.stockmanagment.app.model.Users;
import com.stockmanagment.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(UsersDto::fromEntity).collect(Collectors.toList());
    }

    public UsersDto getUsersById(Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
        return UsersDto.fromEntity(users);
    }

    public UsersDto createUser(UsersDto usersDto) {
        Users users = UsersDto.toEntity(usersDto);
        users = usersRepository.save(users);
        return UsersDto.fromEntity(users);
    }

    public UsersDto updateUser(Long id, UsersDto usersDto) {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        existingUser.setNom(usersDto.getNom());
        existingUser.setPrenom(usersDto.getPrenom());
        existingUser.setMatricule(usersDto.getMatricule());
        existingUser.setEmail(usersDto.getEmail());
        existingUser.setPassword(usersDto.getPassword());

        usersRepository.save(existingUser);
        return UsersDto.fromEntity(existingUser);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
