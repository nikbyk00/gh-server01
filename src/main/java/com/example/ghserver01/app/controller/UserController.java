package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.AuthService;
import com.example.ghserver01.app.service.RegService;
import com.example.ghserver01.app.service.UserService;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.RequiredException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    RegService regService;
    AuthService authService;
    UserService userService;

    @PostMapping("/registration")
    public  ResponseEntity<User> registration (@RequestBody User user) {
        return regService.createUser(user);
    }

    @GetMapping("/activate/{code}")
    public HttpStatus activate (@PathVariable String code) throws RequiredException {
        return regService.activateCode(code);
    }

    @GetMapping("/repeatSending")
    public HttpStatus repeatCode (@RequestBody User user) {
        return regService.sendCode(user);
    }

    @GetMapping(value = "/auth")
    public User authorization (@RequestBody User user) throws RequiredException {
        return authService.authUser(user);
    }

    @GetMapping("/userInfo")
    public Optional<User> userInfo (@RequestBody User user) throws RequiredException {
        return userService.getUserInfo(user);
    }

    @PostMapping("/passwordReset")
    public ResponseEntity<User> passwordReset(User user) {
        return userService.setPassword(user);
    }

}
