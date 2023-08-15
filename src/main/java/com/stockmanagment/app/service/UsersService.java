package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.UsersDto;
import com.stockmanagment.app.dto.UsersRequestDto;
import com.stockmanagment.app.dto.UsersResponseDto;
import com.stockmanagment.app.model.Departement;
import com.stockmanagment.app.model.Role;
import com.stockmanagment.app.model.Users;
import com.stockmanagment.app.repository.DepartementRepository;
import com.stockmanagment.app.repository.RoleRepository;
import com.stockmanagment.app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UsersResponseDto createUser(UsersRequestDto requestDto)
            throws ChangeSetPersister.NotFoundException {
        Users users = convertToEntity(requestDto);
        Users savedUsers = usersRepository.save(users);
        return convertToResponseDto(savedUsers);
    }

    public UsersResponseDto updateUser(Long id, UsersRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        Users existingUsers = usersRepository.findById(id).orElse(null);
        if (existingUsers == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Users updatedUsers = convertToEntity(requestDto);
        updatedUsers.setId(existingUsers.getId());
        Users savedUsers = usersRepository.save(updatedUsers);
        return convertToResponseDto(savedUsers);
    }

    public UsersResponseDto getUserById(Long id) throws ChangeSetPersister.NotFoundException {
        Users users = usersRepository.findById(id).orElse(null);
        if (users == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return convertToResponseDto(users);
    }

    public List<UsersResponseDto> getAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        return usersList.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) throws ChangeSetPersister.NotFoundException {
        Users users = usersRepository.findById(id).orElse(null);
        if (users == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        usersRepository.delete(users);
    }

    private Users convertToEntity(UsersRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        Users users = new Users();

       users.setNom(requestDto.getNom());
        users.setPrenom(requestDto.getPrenom());
        users.setMatricule(requestDto.getMatricule());
        users.setEmail(requestDto.getEmail());
        users.setPassword(requestDto.getPassword());

        Departement departement = departementRepository.findById(requestDto.getDepartementId()).orElse(null);
        if (departement == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        users.setDepartement(departement);

        Role role = roleRepository.findById(requestDto.getRoleId()).orElse(null);
        if (role == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        users.setRole(role);
        return users;
    }
    private UsersResponseDto convertToResponseDto(Users users) {
        UsersResponseDto responseDto = new UsersResponseDto();
        responseDto.setId((users.getId()));
        responseDto.setNom(users.getNom());
        responseDto.setPrenom(users.getPrenom());
        responseDto.setMatricule(users.getMatricule());
        responseDto.setEmail(users.getEmail());
        responseDto.setPassword(users.getPassword());
        responseDto.setDepartementId(users.getDepartement().getId());
        responseDto.setRoleId(users.getRole().getId());
        return responseDto;
    }
}


