package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.AuthService;
import com.example.ghserver01.app.service.RegService;
import com.example.ghserver01.app.service.UserService;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.RequiredException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    RegService regService;
    AuthService authService;
    UserService userService;

    @PostMapping("/registration")
    public  User registration (@RequestBody User user) {
        return regService.sendCodeUser(user);
    }

    @PostMapping("/create")
    public User createUser (@RequestBody User user) throws RequiredException {
        return regService.createUser(user);
    }

    @GetMapping("/repeatSending")
    public User repeatCode (@RequestBody User user) {
        return regService.repeatSend(user);
    }

    @GetMapping(value = "/auth")
    @Transactional
    public User authorization (@RequestBody User user) throws RequiredException {
        return authService.authUser(user);
    }

    @GetMapping("/userInfo")
    public Optional<User> userInfo (@RequestBody User user) throws RequiredException {
        return userService.getUserInfo(user);
    }

    @PostMapping("/passwordReset")
    public User passwordReset(@RequestBody User user) {
        return userService.setUserPassword(user);
    }

}
