package com.example.ghserver01.app.util.Helper;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserHelper {

    private UserRepo userRepo;

    public String getLanguage(Integer id) {
        return userRepo.findById(id).get().getLang();
    }
}
