package com.stockmanagment.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {

    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private String email;
    private String password;
    private DepartementDto departement;
    private RoleDto role;
}