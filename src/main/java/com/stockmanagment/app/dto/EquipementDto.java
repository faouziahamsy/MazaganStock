

package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.EtatEquipement;
import com.stockmanagment.app.model.Equipement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipementDto {
    private Long id;
    private int quantity;
    private String matricule;
    private String photoEquipement;
    private CategoryDto category;
    private EtatEquipement etat;
    private Instant date_entree;
    private Instant date_sortie;

    public static EquipementDto fromEntity(Equipement equipement) {
        if (equipement == null) {
            return null;
        }
        return EquipementDto.builder()
                .id(equipement.getId())
                .quantity(equipement.getQuantity())
                .matricule(equipement.getMatricule())
                .photoEquipement(equipement.getPhotoEquipement())
                .category(CategoryDto.fromEntity(equipement.getCategory()))
                .etat(equipement.getEtat())
                .date_entree(equipement.getDate_entree())
                .date_sortie(equipement.getDate_sortie())
                .build();
    }

    public static List<EquipementDto> fromEntities(List<Equipement> equipements) {
        return equipements.stream()
                .map(EquipementDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static Equipement toEntity(EquipementDto equipementDto) {
        if (equipementDto == null) {
            return null;
        }
        Equipement equipement = new Equipement();
        equipement.setId(equipementDto.getId());
        equipement.setQuantity(equipementDto.getQuantity());
        equipement.setMatricule(equipementDto.getMatricule());
        equipement.setPhotoEquipement(equipementDto.getPhotoEquipement());
        equipement.setCategory(CategoryDto.toEntity(equipementDto.getCategory()));
        equipement.setEtat(equipementDto.getEtat());
        equipement.setDate_entree(equipementDto.getDate_entree());
        equipement.setDate_sortie(equipementDto.getDate_sortie());
        return equipement;
    }

    public static List<Equipement> toEntities(List<EquipementDto> equipementDtos) {
        return equipementDtos.stream()
                .map(EquipementDto::toEntity)
                .collect(Collectors.toList());
    }
}
