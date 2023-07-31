package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.EtatEquipement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipementDto {

    private Long id;
    private String reference;
    private String matricule;
    private String photoEquipement;
    private CategoryDto category;
    private List<ArticleDto> articles;
    private RoomDto salle;
    private EtatEquipement etat;
    private Instant date_entree;
    private Instant date_sortie;
}