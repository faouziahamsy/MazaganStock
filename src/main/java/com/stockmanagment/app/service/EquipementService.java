package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.EquipementRequestDto;
import com.stockmanagment.app.dto.EquipementResponseDto;
import com.stockmanagment.app.model.Article;
import com.stockmanagment.app.model.Category;
import com.stockmanagment.app.model.Equipement;
import com.stockmanagment.app.model.Room;
import com.stockmanagment.app.repository.CategoryRepository;
import com.stockmanagment.app.repository.EquipementRepository;
import com.stockmanagment.app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipementService {

    @Autowired
    private EquipementRepository equipementRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Constructor and other dependencies can be added here

    public EquipementResponseDto createEquipement(EquipementRequestDto requestDTO) {
        // Convert the requestDTO to the entity and perform business logic
        Equipement equipement = convertToEntity(requestDTO);
        equipement.setDate_entree(Instant.now()); // Set the entry date

        // Save the new equipement entity in the repository
        Equipement savedEquipement = equipementRepository.save(equipement);

        // Convert the entity to the responseDTO and return it
        return convertToResponseDTO(savedEquipement);
    }

    private Equipement convertToEntity(EquipementRequestDto requestDTO) {
        Equipement equipement = new Equipement();
        equipement.setQuantity(requestDTO.getQuantity());
        equipement.setMatricule(requestDTO.getMatricule());
        equipement.setEtat(requestDTO.getEtat());

        // Fetch the Category entity using categoryId from the repository
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Category Id"));
        equipement.setCategory(category);

        // Fetch the Room entity using salleId from the repository


        return equipement;
    }

    private EquipementResponseDto convertToResponseDTO(Equipement equipement) {
        EquipementResponseDto responseDTO = new EquipementResponseDto();
        responseDTO.setId(equipement.getId());
        responseDTO.setQuantity(equipement.getQuantity());
        responseDTO.setMatricule(equipement.getMatricule());
        responseDTO.setCategoryId(equipement.getCategory().getId());
        // Assuming articleIds is a list of Long IDs from the List<Article> articles
        responseDTO.setArticleIds(equipement.getArticles().stream()
                .map(Article::getId)
                .collect(Collectors.toList()));
        responseDTO.setSalleId(equipement.getSalle().getId());
        responseDTO.setEtat(equipement.getEtat());
        responseDTO.setDate_entree(equipement.getDate_entree());
        responseDTO.setDate_sortie(equipement.getDate_sortie());
        return responseDTO;
    }
    public EquipementResponseDto findById(Long id) throws ChangeSetPersister.NotFoundException {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return convertToResponseDTO(equipement);
    }

    public List<EquipementResponseDto> findAllEquipements() {
        List<Equipement> equipements = equipementRepository.findAll();
        return equipements.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public EquipementResponseDto updateEquipement(Long id, EquipementRequestDto requestDTO) throws ChangeSetPersister.NotFoundException {
        Equipement existingEquipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        // Update the existing equipement entity with data from the requestDTO
        existingEquipement.setQuantity(requestDTO.getQuantity());
        existingEquipement.setMatricule(requestDTO.getMatricule());
        existingEquipement.setEtat(requestDTO.getEtat());

        // Fetch the Category entity using categoryId from the repository
        Category category = categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Category Id"));
        existingEquipement.setCategory(category);

        // Fetch the Room entity using salleId from the repository
        Room salle = roomRepository.findById(requestDTO.getSalleId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Room Id"));
        existingEquipement.setSalle(salle);

        // Save the updated equipement entity in the repository
        Equipement updatedEquipement = equipementRepository.save(existingEquipement);

        // Convert the entity to the responseDTO and return it
        return convertToResponseDTO(updatedEquipement);
    }

    public void deleteEquipement(Long id) throws ChangeSetPersister.NotFoundException {
        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        equipementRepository.delete(equipement);
    }
    // Add other service methods as needed
}
