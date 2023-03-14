package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.RoomService;
import com.example.ghserver01.app.storage.model.Room;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {
    RoomService roomService;
    @PostMapping("/create")
    public Integer createRoom (@RequestBody Room room){
        return roomService.createRoomService(room);
    }
    @GetMapping("/getRoom")
    public Optional<Room> getRoom (@RequestBody Room room) {
        return roomService.getRoomService(room.getId());
    }
}
