package com.stockmanagment.app.dto;
import com.stockmanagment.app.model.EtatEquipement;


import java.time.Instant;
import java.util.List;


public class EquipementResponseDto {
    private Long id;
    private int quantity;
    private String matricule;
    private Long categoryId;
    private List<Long> articleIds;
    private Long salleId;
    private EtatEquipement etat;
    private Instant date_entree;
    private Instant date_sortie;
}
