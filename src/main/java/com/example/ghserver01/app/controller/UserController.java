package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.service.AuthService;
import com.example.ghserver01.app.service.RegService;
import com.example.ghserver01.app.storage.model.User;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    AuthService authService;
    RegService regService;
    @PostMapping("/registration")
    public void registration (User user) {

    }
}
