package com.stockmanagment.app.dto;


import com.stockmanagment.app.model.EtatEquipement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipementRequestDto {

    private Long id;
    private Long quantity;
    private String matricule;
    private Long categoryId;
    private Long salleId;
    private Instant date_sortie;
    private EtatEquipement etat;
}
