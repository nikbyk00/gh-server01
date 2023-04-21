package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.RequiredException;
import com.example.ghserver01.app.util.Helper.Common;
import com.example.ghserver01.app.util.Mailer;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class UserService {
    UserRepo userRepo;
    Common common;
    Mailer mailer;

    public Optional<User> getUserInfo(User user) {
        Optional<User> userFromDb = userRepo.findById(user.getId());

        if (userFromDb.isEmpty()) {
            throw new RequiredException("user does not exist");
        }

       return userFromDb;
    }

    public ResponseEntity<User> setPassword(User user) {

        User userFromDb = userRepo.findById(user.getId()).get();

        if (!StringUtils.isNullOrEmpty(user.getEmail())) {

            userFromDb.setActivationCode(common.getCode());
            userFromDb.setPassword(user.getPassword());
            mailer.sendMail(user.getEmail(), userFromDb.getActivationCode());

        } else {
            throw new RequiredException("enter your email");
        }

        return new ResponseEntity<>(userFromDb, HttpStatus.OK);
    }
}
