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
public class DepartementDto {

    private Long id;
    private String nom;
    private List<RoomDto> rooms;
    private List<UsersDto> users;
}