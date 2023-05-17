package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Helper.Common;
import com.example.ghserver01.app.util.Mailer;
import com.example.ghserver01.app.util.Value.Constants;
import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegService {
    private UserRepo userRepo;
    private Mailer mailer;
    private Common common;
    private Gson gson;

    public String sendCodeUser(User user) {
        String code = common.getCode();

        if (!StringUtils.isNullOrEmpty(user.getEmail())) {
            sendCode(user.getEmail(), code);
        }

        return gson.toJson(code);
    }

    public User createUser(User user) throws BusinessException {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (userFromDb != null) {
            throw new BusinessException(Constants.USER_ALREADY_EXISTS);
        }

        user.setActivate(true);
        User newUser = userRepo.save(user);

        return newUser;
    }

    private HttpStatus sendCode(String email, String code) {
        mailer.sendMail(email, code);
        return HttpStatus.OK;
    }

}
