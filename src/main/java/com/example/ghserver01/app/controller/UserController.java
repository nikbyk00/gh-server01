package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.AuthService;
import com.example.ghserver01.app.service.RegService;
import com.example.ghserver01.app.service.UserService;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private RegService regService;
    private AuthService authService;
    private UserService userService;

    @PostMapping("/sendCode")
    public String registration(@RequestBody User user) {
        return regService.sendCodeUser(user);
    }

    @PostMapping("/create")
    @Transactional
    public User createUser(@RequestBody User user) throws BusinessException {
        return regService.createUser(user);
    }

    @GetMapping(value = "/auth")
    public User authorization(@RequestParam String email, String password) throws BusinessException {
        return authService.authUser(email, password);
    }

    @GetMapping("/userInfo")
    public Optional<User> userInfo(@RequestParam Integer id) throws BusinessException {
        return userService.getUserInfo(id);
    }

    @PostMapping("/passwordReset")
    public User passwordReset(@RequestBody User user) throws BusinessException {
        return userService.setUserPassword(user);
    }

}
