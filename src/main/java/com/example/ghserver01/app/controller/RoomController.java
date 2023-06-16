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

    @PostMapping("/create")
    public HttpStatus createRoom(@RequestBody Room room,
                                 @RequestParam Integer spaceId,
                                 @RequestParam Boolean isNew) {
        return roomService.createRoom(room, spaceId, isNew);
    }

    @GetMapping("/getRoom")
    public List<Room> getRoom(@RequestParam Integer spaceId){
        return roomService.getRoom(spaceId);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteSpace(@RequestBody Room room) {
        return roomService.deleteRoom(room);
    }
}
