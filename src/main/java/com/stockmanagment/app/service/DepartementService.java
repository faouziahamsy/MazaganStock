package com.stockmanagment.app.service;

import com.stockmanagment.app.model.Departement;
import com.stockmanagment.app.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    public Optional<Departement> getDepartementById(Long id) {
        return departementRepository.findById(id);
    }

    public Departement createDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    public Optional<Departement> updateDepartement(Long id, Departement departementDetails) {
        Optional<Departement> existingDepartement = departementRepository.findById(id);
        if (existingDepartement.isPresent()) {
            Departement departement = existingDepartement.get();
            departement.setNom(departementDetails.getNom());
            // Set other attributes if needed
            return Optional.of(departementRepository.save(departement));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteDepartement(Long id) {
        if (departementRepository.existsById(id)) {
            departementRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}