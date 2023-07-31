package com.stockmanagment.app.service;

import com.stockmanagment.app.model.Users;
import com.stockmanagment.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Optional<Users> updateUser(Long id, Users userDetails) {
        Optional<Users> existingUser = usersRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            user.setNom(userDetails.getNom());
            user.setEmail(userDetails.getEmail());
           // user.setDepartment(userDetails.getDepartment());
           // user.setRoles(userDetails.getRoles());
            // Set other attributes if needed
            return Optional.of(usersRepository.save(user));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteUser(Long id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
