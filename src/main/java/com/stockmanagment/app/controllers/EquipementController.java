package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.CategoryDto;
import com.stockmanagment.app.dto.EquipementDto;
import com.stockmanagment.app.model.Category;
import com.stockmanagment.app.model.Equipement;
import com.stockmanagment.app.model.EtatEquipement;
import com.stockmanagment.app.repository.EquipementRepository;
import com.stockmanagment.app.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("equipements")
public class EquipementController {
    private final EquipementService equipementService;
    @Autowired
    public EquipementController(EquipementService equipementService) {
        this.equipementService = equipementService;
    }
    @CrossOrigin
    @GetMapping
    public List<EquipementDto> getAllEquipements() {
        return equipementService.getAllEquipements();
    }

    @GetMapping("/{id}")
    public EquipementDto getEquipementById(@PathVariable Long id) {
        return equipementService.getEquipementById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Equipement not found with id: " + id));
    }
    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipementDto createEquipement(@RequestParam("photoEquipement") MultipartFile photoEquipement,
                                       @RequestParam("matricule") String matricule,
                                       @RequestParam("category") CategoryDto category,
                                       @RequestParam("quantity") int quantity,
                                       @RequestParam("dateEntree") String dateEntree,
                                       @RequestParam("dateSortie") String dateSortie,
                                       @RequestParam("etat") EtatEquipement etat) {

        EquipementDto equipement = new EquipementDto();
        equipement.setMatricule(matricule);
        equipement.setCategory(category);
        equipement.setQuantity(quantity);
        equipement.setDate_entree(Instant.parse(dateEntree));
        equipement.setDate_sortie(Instant.parse(dateSortie));
        equipement.setEtat(etat);
        try {
            // Convert the image to a byte array and save it in the entity
            byte[] imageBytes = photoEquipement.getBytes();
            equipement.setPhotoEquipement(Arrays.toString(imageBytes));
        } catch (IndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }

        return equipementService.createEquipement(equipement);
    }


    @PutMapping("/{id}")
    public EquipementDto updateEquipement(@PathVariable Long id, @RequestBody EquipementDto equipement) {
        return equipementService.updateEquipement(id, equipement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipement(@PathVariable Long id) {
        equipementService.deleteEquipement(id);
    }
}

