package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.Departement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartementDto {

    private Long id;
    private String nom;
    public static DepartementDto fromEntity(Departement departement) {
        if (departement == null) {
            return null;
        }
        return                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        DepartementDto.builder()
                .id(departement.getId())
                .nom(departement.getNom())

                .build();
    }

    public static Departement toEntity(DepartementDto departementDto) {
        if (departementDto == null) {
            return null;
        }
        Departement departement = new Departement();
        departement.setId(departementDto.getId());
        departement.setNom(departementDto.getNom());

        return departement;
    }
}
