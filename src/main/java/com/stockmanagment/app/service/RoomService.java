package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.RoomDto;
import com.stockmanagment.app.model.Room;
import com.stockmanagment.app.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(RoomDto::fromEntity).collect(Collectors.toList());
    }

    public RoomDto getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Room not found with id: " + id));
        return RoomDto.fromEntity(room);
    }

    public RoomDto createRoom(RoomDto roomDto) {
        Room room = RoomDto.toEntity(roomDto);
        room = roomRepository.save(room);
        return RoomDto.fromEntity(room);
    }

    public RoomDto updateRoom(Long id, RoomDto roomDto) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Room not found with id: " + id));

        existingRoom.setLibelle(roomDto.getLibelle());
        existingRoom.setCodeSalle(roomDto.getCodeSalle());

        roomRepository.save(existingRoom);
        return RoomDto.fromEntity(existingRoom);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
