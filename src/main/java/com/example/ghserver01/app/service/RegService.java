package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Mailer;
import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegService {
    UserRepo userRepo;
    Constants constants;
    Mailer mailer;

    public void createUser (User user) {
        userRepo.save(user);
    }
    public void registrationType (User user) {
        if (user.getType() == Constants.REG_TYPE_CODE) {
            resCode(user);
        } else {
                checkCode(user);
        }
    }
    public void resCode (User user) {
        mailer.sendMail(user.getEmail(), "code");
    }

    public void checkCode (User user) {

    }

}
