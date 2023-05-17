package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GreenHouseService {
    private GreenHouseRepo greenHouseRepo;

    public HttpStatus createGHouse(GreenHouse greenHouse) {

        if (!greenHouse.getIsNew()) {
            GreenHouse greenHouseFromDb = greenHouseRepo.findById(greenHouse.getId()).get();

            greenHouseFromDb.setLandingId(greenHouse.getLandingId());
            greenHouseFromDb.setName(greenHouse.getName());
            greenHouseFromDb.setStatus(greenHouse.getStatus());
            greenHouseFromDb.setUserId(greenHouse.getUserId());
            greenHouseFromDb.setRoomId(greenHouse.getRoomId());
            greenHouseFromDb.setQr(greenHouse.getQr());

            greenHouseRepo.save(greenHouseFromDb);
        }

        greenHouseRepo.save(greenHouse);

        return HttpStatus.OK;
    }

    public List<GreenHouse> getListGHouse(Integer roomId) {
        return greenHouseRepo.findByRoomId(roomId);
    }

    public HttpStatus deleteGHouse(GreenHouse greenHouse) {
        greenHouseRepo.delete(greenHouseRepo.findById(greenHouse.getId()).get());
        return HttpStatus.OK;
    }

    public List<GreenHouse> getStatus(Integer userId) {
        return greenHouseRepo.findByUserId(userId);
    }
}
