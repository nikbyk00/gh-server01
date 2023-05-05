package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.RoomService;
import com.example.ghserver01.app.storage.model.Room;
import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.util.Exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {
    RoomService roomService;
    @PostMapping("/create")
    public Room createRoom (@RequestBody Room room){
        return roomService.createRoom(room);
    }

    @GetMapping("/getRoom")
    public List<Room> getRoom (@RequestBody Room room) throws BusinessException {
        return roomService.getRoom(room);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteSpace (@RequestBody Room room) {
        return roomService.deleteRoom(room);
    }
}
