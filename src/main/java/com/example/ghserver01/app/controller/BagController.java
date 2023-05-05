package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.BagService;
import com.example.ghserver01.app.storage.model.UserBag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/bag")
public class BagController {

    private BagService bagService;

    @PostMapping("/create")
    public HttpStatus createBag(UserBag userBag) {
        return bagService.createBag(userBag);
    }
}
