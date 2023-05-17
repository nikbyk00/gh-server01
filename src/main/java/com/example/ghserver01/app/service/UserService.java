package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepo userRepo;

    public User getUserInfo(Integer id) throws BusinessException {

        try {
            User userFromDb = userRepo.findById(id).get();

            return userFromDb;

        } catch (RuntimeException runtimeException) {
            throw new BusinessException(Constants.USER_IS_NOT_FOUND);
        }
    }

    public User setUserPassword(User user) throws BusinessException {

        try {
            User userFromDb = userRepo.findByEmail(user.getEmail());

            userFromDb.setPassword(user.getPassword());
            userRepo.save(userFromDb);

            return userFromDb;

        } catch (RuntimeException runtimeException) {
            throw new BusinessException(Constants.USER_IS_NOT_FOUND);
        }
    }
}
