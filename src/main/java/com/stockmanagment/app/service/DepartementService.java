package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.DepartementRequestDto;
import com.stockmanagment.app.dto.DepartementResponseDto;
import com.stockmanagment.app.model.Departement;
import com.stockmanagment.app.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public DepartementResponseDto createDepartement(DepartementRequestDto requestDto) {
        Departement departement = new Departement();
        departement.setNom(requestDto.getNom());
        Departement savedDepartement = departementRepository.save(departement);
        return convertToResponseDto(savedDepartement);
    }

    public DepartementResponseDto updateDepartement(Long id, DepartementRequestDto requestDto) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Departement Id"));

        departement.setNom(requestDto.getNom());
        Departement updatedDepartement = departementRepository.save(departement);
        return convertToResponseDto(updatedDepartement);
    }

    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }

    public DepartementResponseDto getDepartementById(Long id) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Departement Id"));

        return convertToResponseDto(departement);
    }

    public List<DepartementResponseDto> getAllDepartements() {
        List<Departement> departements = departementRepository.findAll();
        return departements.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private DepartementResponseDto convertToResponseDto(Departement departement) {
        DepartementResponseDto responseDto = new DepartementResponseDto();
        responseDto.setId(departement.getId());
        responseDto.setNom(departement.getNom());
        // You can include any other fields you want to return in the response.
        return responseDto;
    }
}
