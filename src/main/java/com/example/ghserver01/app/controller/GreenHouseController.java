package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.GreenHouseService;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greenHouse")
@AllArgsConstructor
public class GreenHouseController {
    GreenHouseService greenHouseService;
    @PostMapping("/create")
    public HttpStatus createGreenHouse(GreenHouse greenHouse) {
        return greenHouseService.createGHouse(greenHouse);
    }

    @GetMapping("get")
    public List<GreenHouse> getListGHouse(Room room) {
        return greenHouseService.getListGHouse(room);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteGHouse(GreenHouse greenHouse) {
        return greenHouseService.deleteGHouse(greenHouse);
    }

    @GetMapping("/getStatus")
    public List <GreenHouse> getStatus(GreenHouse greenHouse) {
        return greenHouseService.getStatus(greenHouse);
    }
 }
