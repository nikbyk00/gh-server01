package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    RoomRepo roomRepo;
    public Room createRoomService (Room room){

         if (room.getId() != null) {
             Room roomFromDb = roomRepo.findById(room.getId()).get();

             roomFromDb.setNew(false);
             roomFromDb.setName(room.getName());
             roomFromDb.setSpaceId(room.getSpaceId());

             return roomFromDb;
         }

        Room newRoom = roomRepo.save(room);
        room.setNew(true);

        return newRoom;
    }

    public List<Room> getRoomService (Space space) {
        List<Room> room = roomRepo.findBySpaceId(space.getId());
        return room;
    }

    public HttpStatus deleteRoom(Room room) {
        roomRepo.delete(roomRepo.findById(room.getId()).get());
        return HttpStatus.OK;
    }
}
