package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.RoomService;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.util.Exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {
    private RoomService roomService;

    @PostMapping(value = "/create")
    public HttpStatus create(@RequestBody Room room,
                                 @RequestParam Integer spaceId,
                                 @RequestParam Boolean isNew) {
        return roomService.createRoom(room, spaceId, isNew);
    }

    @GetMapping(value = "/get")
    public List<Room> get(@RequestParam Integer spaceId) {
        return roomService.getRoom(spaceId);
    }

    @DeleteMapping(value = "/delete")
    public HttpStatus delete(@RequestParam Integer roomId) {
        return roomService.deleteRoom(roomId);
    }

}
