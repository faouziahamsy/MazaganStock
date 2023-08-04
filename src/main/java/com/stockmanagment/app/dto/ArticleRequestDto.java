
 package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.EtatEquipement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ArticleRequestDto {

        private Long quantity;
        private String matricule;
        private EtatEquipement etat;

        private Instant date_sortie;


    }


