package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.EtatEquipement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipementRequestDto {
    private int quantity;
    private String matricule;
    private Long categoryId;
    private Long salleId;
    private EtatEquipement etat;
}
