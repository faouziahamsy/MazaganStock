package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.RoleDto;
import com.stockmanagment.app.model.Role;
import com.stockmanagment.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleDto::fromEntity).collect(Collectors.toList());
    }

    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id: " + id));
        return RoleDto.fromEntity(role);
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = RoleDto.toEntity(roleDto);
        role = roleRepository.save(role);
        return RoleDto.fromEntity(role);
    }

    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with id: " + id));

        existingRole.setName(roleDto.getName());

        roleRepository.save(existingRole);
        return RoleDto.fromEntity(existingRole);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
