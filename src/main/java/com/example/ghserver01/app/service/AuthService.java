package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.ExceptionAuth;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class AuthService {
    UserRepo userRepo;
    public User authUser(User user) throws ExceptionAuth {
        User userFromDb = userRepo.findByPassword(user.getPassword());

        if (userFromDb == null) {
            throw new ExceptionAuth("invalid Password");
        }

        if (StringUtils.isNullOrEmpty(userFromDb.getEmail())) {
            throw new ExceptionAuth("invalid login");
        }

        return userFromDb;
    }
}
