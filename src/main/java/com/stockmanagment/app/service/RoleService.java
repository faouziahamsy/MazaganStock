package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.RoleDto;
import com.stockmanagment.app.dto.RoleRequestDto;
import com.stockmanagment.app.dto.RoleResponseDto;
import com.stockmanagment.app.model.Role;
import com.stockmanagment.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleResponseDto createRole(RoleRequestDto requestDto) {
        Role role = Role.builder()
                .nom(requestDto.getNom())
                .build();

        Role savedRole = roleRepository.save(role);

        return convertToResponseDto(savedRole);
    }

    public RoleResponseDto getRoleById(Long id) throws ChangeSetPersister.NotFoundException {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        return convertToResponseDto(role);
    }

    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public RoleResponseDto updateRole(Long id, RoleRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        role.setNom(requestDto.getNom());
        Role updatedRole = roleRepository.save(role);

        return convertToResponseDto(updatedRole);
    }

    public void deleteRole(Long id) throws ChangeSetPersister.NotFoundException {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        roleRepository.delete(role);
    }

    private RoleResponseDto convertToResponseDto(Role role) {
        return new RoleResponseDto(role.getId(), role.getNom());
    }
}
