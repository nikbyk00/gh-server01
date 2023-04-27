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
    GreenHouseRepo greenHouseRepo;
    public HttpStatus createGHouse(GreenHouse greenHouse) {
        greenHouseRepo.save(greenHouse);
        return HttpStatus.OK;
    }

    public List<GreenHouse> getListGHouse(Room room) {
        return greenHouseRepo.findByRoomId(room.getId());
    }

    public HttpStatus deleteGHouse(GreenHouse greenHouse) {
        greenHouseRepo.delete(greenHouseRepo.findById(greenHouse.getId()).get());
        return HttpStatus.OK;
    }
}
