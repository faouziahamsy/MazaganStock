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
public class ArticleResponseDto {
    private Long id;
    private EtatEquipement etat;
    private Long quantity;
    private String matricule;
    private Instant date_entree;
    private Instant date_sortie;
  //  private List<Long> equipementIds;
}
