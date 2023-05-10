package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomRepo roomRepo;

    public Room createRoom(Room room) {

        if (!room.getIsNew()) {
            Room roomFromDb = roomRepo.findById(room.getId()).get();

            roomFromDb.setName(room.getName());
            roomFromDb.setSpaceId(room.getSpaceId());

            roomRepo.save(roomFromDb);

            return roomFromDb;
        }

        Room newRoom = roomRepo.save(room);

        return newRoom;
    }

    public List<Room> getRoom(Integer spaceId) throws BusinessException {
        List<Room> roomFromDb = roomRepo.findBySpaceId(spaceId);

        if (roomFromDb.isEmpty()) {
            throw new BusinessException(Constants.ROOM_NOT_FOUND);
        }

        return roomFromDb;
    }

    public HttpStatus deleteRoom(Room room) {
        roomRepo.delete(roomRepo.findById(room.getId()).get());
        return HttpStatus.OK;
    }
}
