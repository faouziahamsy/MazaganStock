package com.stockmanagment.app.controllers;
import com.stockmanagment.app.dto.DepartementDto;
import com.stockmanagment.app.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {
    private final DepartementService departementService;

    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public List<DepartementDto> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public DepartementDto getDepartementById(@PathVariable Long id) {
        return departementService.getDepartementById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departement not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartementDto createDepartement(@RequestBody DepartementDto departement) {
        return departementService.createDepartement(departement);
    }

    @PutMapping("/{id}")
    public DepartementDto updateDepartement(@PathVariable Long id, @RequestBody DepartementDto departement) {
        return departementService.updateDepartement(id, departement)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departement not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
    }
}

