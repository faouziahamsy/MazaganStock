package com.stockmanagment.app.dto;

import com.stockmanagment.app.model.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

    private Long id;
    private String libelle;
    private String codeSalle;

    public static RoomDto fromEntity(Room room) {
        if (room == null) {
            return null;
        }
        return RoomDto.builder()
                .id(room.getId())
                .libelle(room.getLibelle())
                .codeSalle(room.getCodeSalle())
                .build();
    }

    public static Room toEntity(RoomDto roomDto) {
        if (roomDto == null) {
            return null;
        }
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setLibelle(roomDto.getLibelle());
        room.setCodeSalle(roomDto.getCodeSalle());
        return room;
    }
}

