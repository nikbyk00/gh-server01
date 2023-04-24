package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Space;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GreenHouseService {
    GreenHouseRepo greenHouseRepo;
    public GreenHouse createGreenHouse (GreenHouse greenHouse) {

        if (greenHouse.getId() != null) {
            GreenHouse gHouseFromDb = greenHouseRepo.findById(greenHouse.getId()).get();



            return gHouseFromDb;
        }

        return greenHouseRepo.save(greenHouse);
    }
    public GreenHouse getGreenHouseService (Integer id) {
        GreenHouse greenHouse = greenHouseRepo.findById(id).get();
        return greenHouse;
    }
}
