package com.stockmanagment.app.controllers;

import com.stockmanagment.app.model.Departement;
import com.stockmanagment.app.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {
    private final DepartementService departementService;

    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public Departement getDepartementById(@PathVariable Long id) {
        return departementService.getDepartementById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Departement not found with id: " + id));
    }

    @PostMapping
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementService.createDepartement(departement);
    }

    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable Long id, @RequestBody Departement departementDetails) {
        return departementService.updateDepartement(id, departementDetails)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Departement not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public boolean deleteDepartement(@PathVariable Long id) {
        return departementService.deleteDepartement(id);
    }
}