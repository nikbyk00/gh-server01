package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Helper.Common;
import com.example.ghserver01.app.util.Mailer;
import com.example.ghserver01.app.util.Value.Constants;
import com.mysql.cj.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    public Optional<User> getUserInfo(Integer id) throws BusinessException {
        Optional<User> userFromDb = userRepo.findById(id);

        if (userFromDb.isEmpty()) {
            throw new BusinessException(Constants.USER_IS_NOT_FOUND);
        }

        return userFromDb;
    }

    public User setUserPassword(User user) throws BusinessException {
        User userFromDb = userRepo.findByEmail(user.getEmail());

        if (userFromDb == null) {
            throw new BusinessException(Constants.USER_IS_NOT_FOUND);
        }

        userFromDb.setActivationCode(null);
        userFromDb.setActivate(true);
        userFromDb.setPassword(user.getPassword());
        userRepo.save(userFromDb);

        return userFromDb;
    }
}
