package com.stockmanagment.app.service;

import com.stockmanagment.app.model.Equipement;
import com.stockmanagment.app.repository.EquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;



@Service
public class EquipementService {

    private final EquipementRepository equipementRepository;

    @Autowired
    public EquipementService(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    public List<Equipement> getAllEquipements() {
        return equipementRepository.findAll();
    }

    public Optional<Equipement> getEquipementById(Long id) {
        return equipementRepository.findById(id);
    }

    public Equipement createEquipement(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public Equipement updateEquipement(Long id, Equipement equipement) {
        Equipement existingEquipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Equipement not found with id: " + id));

        existingEquipement.setReference(equipement.getReference());
        existingEquipement.setMatricule(equipement.getMatricule());
        existingEquipement.setPhotoEquipement(equipement.getPhotoEquipement());
        existingEquipement.setCategory(equipement.getCategory());

        return equipementRepository.save(existingEquipement);
    }

    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }
}