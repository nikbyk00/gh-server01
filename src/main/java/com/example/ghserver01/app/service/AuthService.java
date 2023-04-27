package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.RequiredException;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    UserRepo userRepo;
    public User authUser(User user) throws RequiredException {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (!userFromDb.getPassword().equals(user.getPassword()) ||
            !userFromDb.getEmail().equals(user.getEmail())) {
            throw new RequiredException("неверный логин или пароль");
        }

        return userFromDb;
    }
}
