package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepo userRepo;

    public User authUser(User user) throws BusinessException {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (!userFromDb.getPassword().equals(user.getPassword()) ||
            !userFromDb.getEmail().equals(user.getEmail())) {
            throw new BusinessException(Constants.WRONG_LOGIN_OR_PASSWORD);
        }

        return userFromDb;
    }
}
