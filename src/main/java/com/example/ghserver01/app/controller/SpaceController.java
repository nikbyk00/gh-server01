package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.service.SpaceService;
import com.example.ghserver01.app.storage.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/space")
@AllArgsConstructor
public class SpaceController {
    private SpaceService spaceService;
    @PostMapping("/create")
    public Space spaceCreate (@RequestBody Space space){
        return spaceService.createSpaceService(space);
    }

    @GetMapping("/getSpace")
    public List<Space> getSpace (@RequestBody User user) {
        return spaceService.getSpaceService(user);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteSpace (@RequestBody Space space) {
        return spaceService.deleteSpace(space);
    }

}

