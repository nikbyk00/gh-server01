package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GreenHouseService {
    private GreenHouseRepo greenHouseRepo;
    private final RoomRepo roomRepo;
    private final SpaceRepo spaceRepo;

    public HttpStatus createGHouse(GreenHouse greenHouse, Integer roomId) {
        GreenHouse greenHouseFromDb = greenHouseRepo.findById(greenHouse.getId()).get();
        updateGreenHouseFields(greenHouseFromDb,greenHouse);

        Room room = roomRepo.findById(roomId).get();
        room.getGreenHouseList().add(greenHouseFromDb);
        roomRepo.save(room);

        greenHouseRepo.save(greenHouseFromDb);

        return HttpStatus.OK;
    }

    public List<GreenHouse> getListGHouse(Integer roomId) {
        Room room = roomRepo.findById(roomId).get();
        return room.getGreenHouseList();
    }

    private void updateGreenHouseFields(GreenHouse greenHouseFromDb, GreenHouse newGreenHouse) {
        greenHouseFromDb.setName(newGreenHouse.getName());
        greenHouseRepo.save(greenHouseFromDb);
    }

    public List<GreenHouse> getStatusGHouse(Integer userId) {

        List<Space> spaceFromDb = spaceRepo.findByUserId(userId);
        List<GreenHouse> greenHouseList = new ArrayList<>();

        for (int i = 0; i < spaceFromDb.size(); i++) {
            Space space = spaceFromDb.get(i);
            List<Room> roomList = space.getRoomList();
            for (int j = 0; j < roomList.size(); j++) {
                Room room = roomList.get(j);
                greenHouseList = room.getGreenHouseList();
            }
        }

        return greenHouseList;

    }
}
