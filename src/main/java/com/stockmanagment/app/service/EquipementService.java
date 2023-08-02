package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.CategoryDto;
import com.stockmanagment.app.dto.EquipementDto;
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

    public List<EquipementDto> getAllEquipements() {
        // You need to convert the list of Equipement entities to a list of EquipementDto
        List<Equipement> equipements = equipementRepository.findAll();
        return EquipementDto.fromEntities(equipements);
    }

    public Optional<EquipementDto> getEquipementById(Long id) {
        Optional<Equipement> equipementOptional = equipementRepository.findById(id);
        return equipementOptional.map(EquipementDto::fromEntity);
    }

    public EquipementDto createEquipement(EquipementDto equipement) {
        Equipement newEquipement = EquipementDto.toEntity(equipement);
        Equipement savedEquipement = equipementRepository.save(newEquipement);
        return EquipementDto.fromEntity(savedEquipement);
    }

    public EquipementDto updateEquipement(Long id, EquipementDto equipement) {
        Equipement existingEquipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipement not found with id: " + id));

        // Update the existingEquipement entity with the values from the EquipementDto
        existingEquipement.setQuantity(equipement.getQuantity());
        existingEquipement.setMatricule(equipement.getMatricule());
        existingEquipement.setPhotoEquipement(equipement.getPhotoEquipement());

        // You should convert the category from the EquipementDto to the Category entity and set it in existingEquipement
        existingEquipement.setCategory(CategoryDto.toEntity(equipement.getCategory()));

        Equipement updatedEquipement = equipementRepository.save(existingEquipement);
        return EquipementDto.fromEntity(updatedEquipement);
    }

    public void deleteEquipement(Long id) {
        equipementRepository.deleteById(id);
    }
}
