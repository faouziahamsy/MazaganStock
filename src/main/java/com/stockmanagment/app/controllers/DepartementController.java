package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.DepartementRequestDto;
import com.stockmanagment.app.dto.DepartementResponseDto;
import com.stockmanagment.app.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @PostMapping
    public ResponseEntity<DepartementResponseDto> createDepartement(@RequestBody DepartementRequestDto requestDto) {
        DepartementResponseDto responseDto = departementService.createDepartement(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartementResponseDto> updateDepartement(
            @PathVariable Long id, @RequestBody DepartementRequestDto requestDto) {
        DepartementResponseDto responseDto = departementService.updateDepartement(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Long id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartementResponseDto> getDepartementById(@PathVariable Long id) {
        DepartementResponseDto responseDto = departementService.getDepartementById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<DepartementResponseDto>> getAllDepartements() {
        List<DepartementResponseDto> responseDtoList = departementService.getAllDepartements();
        return ResponseEntity.ok(responseDtoList);
    }
}
