package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.GreenHouseService;
import com.example.ghserver01.app.storage.model.GreenHouse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/greenHouse")
@AllArgsConstructor
public class GreenHouseController {
    GreenHouseService greenHouseService;
    @PostMapping("/create")
    public Integer createGreenHouse (@RequestBody GreenHouse greenHouse) {
        return greenHouseService.createGreenHouse(greenHouse);
    }
    @GetMapping("/get")
    public Optional<GreenHouse> getGreenHouse (@RequestBody GreenHouse greenHouse) {
        return greenHouseService.getGreenHouseService(greenHouse.getId());
    }
}
