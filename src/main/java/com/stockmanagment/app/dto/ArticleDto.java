package com.stockmanagment.app.dto;

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
public class ArticleDto {

    private Long id;
    private String reference;
    private String codeBarre;
    private String matricule;
    private String photoArticle;
    private Instant date_entree;
    private Instant date_sortie;
    private List<EquipementDto> equipements;
}