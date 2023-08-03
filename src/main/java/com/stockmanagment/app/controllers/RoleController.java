package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.RoleDto;
import com.stockmanagment.app.dto.RoleRequestDto;
import com.stockmanagment.app.dto.RoleResponseDto;
import com.stockmanagment.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto requestDto) {
        RoleResponseDto responseDto = roleService.createRole(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        RoleResponseDto responseDto = roleService.getRoleById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
        List<RoleResponseDto> responseDtoList = roleService.getAllRoles();
        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> updateRole(@PathVariable Long id, @RequestBody RoleRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        RoleResponseDto responseDto = roleService.updateRole(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
