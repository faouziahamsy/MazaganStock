package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.Users;
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

    public static UsersDto fromEntity(Users users) {
        if (users == null) {
            return null;
        }
        return UsersDto.builder()
                .id(users.getId())
                .nom(users.getNom())
                .prenom(users.getPrenom())
                .matricule(users.getMatricule())
                .email(users.getEmail())
                .password(users.getPassword())
                .build();
    }

    public static Users toEntity(UsersDto usersDto) {
        if (usersDto == null) {
            return null;
        }
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setNom(usersDto.getNom());
        users.setPrenom(usersDto.getPrenom());
        users.setMatricule(usersDto.getMatricule());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        return users;
    }
}
