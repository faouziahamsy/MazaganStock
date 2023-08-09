package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.EquipementRequestDto;
import com.stockmanagment.app.dto.EquipementResponseDto;
import com.stockmanagment.app.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/equipements")
public class EquipementController {

    @Autowired
    private EquipementService equipementService;
    // Constructor and other dependencies can be added here
    @PostMapping
    public ResponseEntity<EquipementResponseDto> createEquipement(@RequestBody EquipementRequestDto requestDTO) {
        // Call the service method to create the equipement
        EquipementResponseDto responseDTO = equipementService.createEquipement(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipementResponseDto> getEquipementById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        EquipementResponseDto responseDTO = equipementService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EquipementResponseDto>> getAllEquipements() {
        List<EquipementResponseDto> responseDTOList = equipementService.findAllEquipements();
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/all")
    public ResponseEntity<Long> getTotalEquipments() {
        long totalEquipments = equipementService.getTotalEquipments();
        return ResponseEntity.ok(totalEquipments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipementResponseDto> updateEquipement(
            @PathVariable Long id, @RequestBody EquipementRequestDto requestDTO) throws ChangeSetPersister.NotFoundException {
        EquipementResponseDto responseDTO = equipementService.updateEquipement(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipement(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        equipementService.deleteEquipement(id);
        return ResponseEntity.noContent().build();
    }

    // Add other controller methods as needed
}
