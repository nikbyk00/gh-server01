package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.SpaceService;
import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/space")
@AllArgsConstructor
public class SpaceController {
    private SpaceService spaceService;

    @PostMapping(value = "/create")
    public HttpStatus createSpace(@RequestBody Space space,
                                  @RequestParam Boolean isNew, @RequestParam Integer userId) {
        return spaceService.createSpace(space, isNew, userId);
    }

    @GetMapping("/getSpace")
    public List<Space> getSpace(@RequestParam Integer userId) {
        return spaceService.getSpace(userId);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteSpace(@RequestParam Integer spaceId) {
        return spaceService.deleteSpace(spaceId);
    }

}

