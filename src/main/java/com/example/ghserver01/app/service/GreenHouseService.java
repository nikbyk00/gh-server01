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
        greenHouseRepo.save(greenHouse);
        return greenHouse.getId();
    }
    public Optional<GreenHouse> getGreenHouseService (Integer id) {
        Optional<GreenHouse> greenHouse = greenHouseRepo.findById(id);
        return greenHouse;
    }
}
