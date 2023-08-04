package com.stockmanagment.app.dto;
import com.stockmanagment.app.model.EtatEquipement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EquipementResponseDto {
    private Long id;
    private Long quantity;
    private String matricule;
    private Long categoryId;
   // private List<Long> articleIds;
    private Long salleId;
    private EtatEquipement etat;
    private Instant date_entree;
    private Instant date_sortie;
}
