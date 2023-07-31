package com.stockmanagment.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    private String libelle;
    private String codeSalle;
    private DepartementDto departement;
    private List<EquipementDto> equipements;
}