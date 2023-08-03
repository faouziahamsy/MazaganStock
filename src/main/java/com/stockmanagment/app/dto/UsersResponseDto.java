package com.stockmanagment.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponseDto {
    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private String email;
    private String password;
    private Long departementId;
    private Long roleId;
}
