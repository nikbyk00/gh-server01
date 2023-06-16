package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.RoomRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GreenHouseService {
    private GreenHouseRepo greenHouseRepo;
    private final RoomRepo roomRepo;

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

    public HttpStatus deleteGHouse(GreenHouse greenHouse) {
        greenHouseRepo.delete(greenHouseRepo.findById(greenHouse.getId()).get());
        return HttpStatus.OK;
    }

    private void updateGreenHouseFields(GreenHouse greenHouseFromDb, GreenHouse newGreenHouse) {
        greenHouseFromDb.setName(newGreenHouse.getName());
        greenHouseRepo.save(greenHouseFromDb);
    }

}
