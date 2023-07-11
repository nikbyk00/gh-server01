package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomService {
    private RoomRepo roomRepo;
    private SpaceRepo spaceRepo;

    public HttpStatus createRoom(Room room, Integer spaceId, Boolean isNew) {

        if (isNew) {
            Space space = spaceRepo.findById(spaceId).get();
            Room roomFromDb = roomRepo.save(new Room(room.getName(), space));
            space.getRoomList().add(roomFromDb);
            spaceRepo.save(space);

            return HttpStatus.OK;
        }

        Room roomFromDb = roomRepo.findById(room.getId()).get();
        roomFromDb.setName(room.getName());
        roomRepo.save(roomFromDb);

        return HttpStatus.OK;
    }

    public List<Room> getRoom(Integer spaceId) {
         return spaceRepo.findById(spaceId).get().getRoomList();
    }

    public HttpStatus deleteRoom(Integer roomId) {
        Room roomFromDb = roomRepo.findById(roomId).get();
        Space spaceFromDb = spaceRepo.findById(roomFromDb.getSpace().getId()).get();
        List<Room> updatedListRoom = spaceFromDb.getRoomList().
                stream().
                filter(room -> !room.getId().equals(roomId)).
                collect(Collectors.toList());

        spaceFromDb.setRoomList(updatedListRoom);
        spaceRepo.save(spaceFromDb);
        roomRepo.delete(roomFromDb); //save??

        return HttpStatus.OK;
    }
}
