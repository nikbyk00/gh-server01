package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.RequiredException;
import com.example.ghserver01.app.util.Helper.Common;
import com.example.ghserver01.app.util.Mailer;
import com.example.ghserver01.app.util.Value.Constants;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegService {
    UserRepo userRepo;
    Mailer mailer;
    Common common;
    RequiredException errorAuth;

    public ResponseEntity<User> createUser (User user) throws RequiredException {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (userFromDb != null) {
            throw new RequiredException("the user already exists");
        }

        user.setActivationCode(common.getCode());
        userRepo.save(user);

        if (!StringUtils.isNullOrEmpty(user.getEmail())) {
            sendCode(user);
        }

        return new ResponseEntity(userRepo.findById(user.getId()), HttpStatus.OK);
    }

    public HttpStatus activateCode(String code) throws RequiredException {
        User userFromDb = userRepo.findByActivationCode(code);

        if (userFromDb == null) {
            throw new RequiredException("Invalid code");
        }

        userFromDb.setActivationCode(null);
        userFromDb.setActivate(true);
        userRepo.save(userFromDb);

        return HttpStatus.OK;

    }

    public HttpStatus sendCode (User user) {
        if (!StringUtils.isNullOrEmpty(user.getActivationCode())) {
            mailer.sendMail(user.getEmail(), user.getActivationCode());
        }
        return HttpStatus.OK;
    }
}
