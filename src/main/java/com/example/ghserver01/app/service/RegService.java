package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Helper.Common;
import com.example.ghserver01.app.util.Mailer;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegService {
    private UserRepo userRepo;
    private Mailer mailer;
    private Common common;

    public User sendCodeUser (User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

//        if (userFromDb != null) {
//            throw new RequiredException("the user already exists");
//        }

        user.setActivationCode(common.getCode());
        User userResp = userRepo.save(user);

        if (!StringUtils.isNullOrEmpty(user.getEmail())) {
            sendCode(user);
        }

        return userResp;
    }

    public User createUser(User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

//        if (userFromDb == null) {
//            throw new RequiredException("Invalid code");
//        }

        userFromDb.setActivationCode(null);
        userFromDb.setActivate(true);
        userFromDb.setPassword(user.getPassword());
        userFromDb.setUsername(user.getUsername());
        userRepo.save(userFromDb);

        return userFromDb;

    }

    private HttpStatus sendCode (User user) {
        if (!StringUtils.isNullOrEmpty(user.getActivationCode())) {
            mailer.sendMail(user.getEmail(), user.getActivationCode());
        }
        return HttpStatus.OK;
    }

    public User repeatSend(User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        userFromDb.setActivationCode(common.getCode());
        userRepo.save(userFromDb);

        if (!StringUtils.isNullOrEmpty(user.getEmail())) {
            sendCode(userFromDb);
        }

        return userFromDb;
    }
}
