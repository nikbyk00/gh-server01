package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Helper.UserHelper;
import com.example.ghserver01.app.util.Value.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpaceService {
    private SpaceRepo spaceRepo;
    private UserRepo userRepo;

    public HttpStatus createSpace(Space space, Integer userId, Boolean isNew) {

        if (isNew) {
            spaceRepo.save(space);
           // Space spaceFromDb = spaceRepo.fi
            User userFromDb = userRepo.findById(userId).get();
           // userFromDb.getSpaceList().add(space);
            userRepo.save(userFromDb);

            return HttpStatus.OK;
        }

        Space spaceFromDb = spaceRepo.findById(space.getId()).get();
        updateFieldsRoom(spaceFromDb, space);

        return HttpStatus.OK;
    }

    public List<Space> getSpace(Integer userId) {
        User userFromDb = userRepo.findById(userId).get();
        return null;
    }

    public HttpStatus deleteSpace(Integer spaceId) {
        spaceRepo.delete(spaceRepo.findById(spaceId).get());
        return HttpStatus.OK;
    }

    private void updateFieldsRoom(Space spaceFromDb, Space newSpace) {
        spaceFromDb.setColor(newSpace.getColor());
        spaceFromDb.setName(newSpace.getName());
        spaceRepo.save(spaceFromDb);
    }
}

