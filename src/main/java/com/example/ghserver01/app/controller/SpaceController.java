package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.service.SpaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/space")
@AllArgsConstructor
public class SpaceController {
    private SpaceService spaceService;
    @PostMapping("/create")
    public Integer spaceCreate (@RequestBody Space space){
        return spaceService.createSpaceService(space);
    }
    @GetMapping("/getSpace")
    public Optional<Space> getSpace (@RequestBody Space space) {
        return spaceService.getSpaceService(space.getId());
    }
}

