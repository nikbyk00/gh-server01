package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.storage.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService {
    RoomRepo roomRepo;
    public Integer createRoomService (Room room){
        Room roomSave = new Room(room.getName(), room.getSpaceId());
        roomRepo.save(roomSave);
        return roomSave.getId();
    }

    public Optional<Room> getRoomService (Integer id) {
        Optional<Room> room = roomRepo.findById(id);
        return room;
    }
}
