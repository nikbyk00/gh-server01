package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GreenHouseService {
    GreenHouseRepo greenHouseRepo;
    public Integer createGreenHouse (GreenHouse greenHouse) {
        GreenHouse greenHouseSave = new GreenHouse(greenHouse.getName(), greenHouse.getRoomId(),
                greenHouse.getAdditionalTemperature(), greenHouse.getTemperature(), greenHouse.getEc(),
                greenHouse.getCo2(), greenHouse.getIlluminance(), greenHouse.getLiquidLevel(),
                greenHouse.getVentilation(), greenHouse.getWashingInterval(), greenHouse.getPh());

        greenHouseRepo.save(greenHouseSave);
        return greenHouseSave.getId();
    }
    public Optional<GreenHouse> getGreenHouseService (Integer id) {
        Optional<GreenHouse> greenHouse = greenHouseRepo.findById(id);
        return greenHouse;
    }
}
