package com.stockmanagment.app.service;
import com.stockmanagment.app.dto.DepartementDto;
import com.stockmanagment.app.model.Departement;
import com.stockmanagment.app.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public List<DepartementDto> getAllDepartements() {
        List<Departement> departements = departementRepository.findAll();
        return departements.stream()
                .map(DepartementDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<DepartementDto> getDepartementById(Long id) {
        return departementRepository.findById(id)
                .map(DepartementDto::fromEntity);
    }

    public DepartementDto createDepartement(DepartementDto departement) {
        Departement entity = DepartementDto.toEntity(departement);
        Departement savedDepartement = departementRepository.save(entity);
        return DepartementDto.fromEntity(savedDepartement);
    }

    public Optional<DepartementDto> updateDepartement(Long id, DepartementDto departementDto) {
        return departementRepository.findById(id).map(existingDepartement -> {
            Departement updatedDepartement = DepartementDto.toEntity(departementDto);
            updatedDepartement.setId(existingDepartement.getId());
            return DepartementDto.fromEntity(departementRepository.save(updatedDepartement));
        });
    }

    public void deleteDepartement(Long id) {
        departementRepository.deleteById(id);
    }
}
