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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpaceService {
    private SpaceRepo spaceRepo;
    private final UserRepo userRepo;

    public HttpStatus createSpace(Space space, Boolean isNew, Integer userId) {

        if (isNew) {
            User user = userRepo.findById(userId).get();
            Space spaceFromDb = spaceRepo.save(new Space(space.getName(), space.getColor(), user));
            user.getSpace().add(spaceFromDb);
            userRepo.save(user);

            return HttpStatus.OK;
        }

        Space spaceFromDb = spaceRepo.findById(space.getId()).get();
        updateFieldsSpace(spaceFromDb, space);

        return HttpStatus.OK;
    }

    public List<Space> getSpace(Integer userId) {
        return userRepo.findById(userId).get().getSpace();
    }

    public HttpStatus deleteSpace(Integer spaceId) {
        Space spaceFromDb = spaceRepo.findById(spaceId).get();
        User userFromDb = userRepo.findById(spaceFromDb.getUser().getId()).get();

        List<Space> updatedList = userFromDb.getSpace().
                stream().
                filter(space -> !space.getId().equals(spaceId)).
                collect(Collectors.toList());

        userFromDb.setSpace(updatedList);
        userRepo.save(userFromDb);
        spaceRepo.delete(spaceFromDb); //save??

        return HttpStatus.OK;
    }

    private void updateFieldsSpace(Space spaceFromDb, Space newSpace) {
        spaceFromDb.setColor(newSpace.getColor());
        spaceFromDb.setName(newSpace.getName());
        spaceRepo.save(spaceFromDb);
    }
}

