package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.GreenHouseService;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.util.Exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greenHouse")
@AllArgsConstructor
public class GreenHouseController {
    private GreenHouseService greenHouseService;

    @PostMapping("/create")
    public HttpStatus createGreenHouse(@RequestBody GreenHouse greenHouse,
                                       @RequestParam Integer roomId) {
        return greenHouseService.createGHouse(greenHouse, roomId);
    }

    @GetMapping("/get")
    public List<GreenHouse> getListGHouse(@RequestParam Integer roomId) {
        return greenHouseService.getListGHouse(roomId);
    }

    @GetMapping("/getStatus")
    public List<GreenHouse> getStatus(@RequestParam Integer userId) {
        return greenHouseService.getStatusGHouse(userId);
    }

}
