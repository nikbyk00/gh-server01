package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomRepo roomRepo;
    private SpaceRepo spaceRepo;

    public HttpStatus createRoom(Room room, Integer spaceId, Boolean isNew) {

        if (isNew) {
            roomRepo.save(room);
            Space spaceFromDb = spaceRepo.findById(spaceId).get();
            spaceFromDb.getRoomList().add(room);
            spaceRepo.save(spaceFromDb);

            return HttpStatus.OK;
        }

        Room roomFromDb = roomRepo.findById(room.getId()).get();
        roomFromDb.setName(room.getName());
        roomRepo.save(roomFromDb);

        return HttpStatus.OK;
    }

    public List<Room> getRoom(Integer spaceId) {
        Space spaceFromDb = spaceRepo.findById(spaceId).get();
        return spaceFromDb.getRoomList();
    }

}
