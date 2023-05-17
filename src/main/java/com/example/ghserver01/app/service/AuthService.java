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

    public User authUser(String email, String password) throws BusinessException {

        try {
            User userFromDb = userRepo.findByEmail(email);

            if (!userFromDb.getPassword().equals(password)) {
                throw new BusinessException(Constants.WRONG_LOGIN_OR_PASSWORD);
            }

            return userFromDb;

        } catch (RuntimeException ex) {
            throw new BusinessException(Constants.WRONG_LOGIN_OR_PASSWORD);
        }

    }
}
