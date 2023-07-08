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

    public HttpStatus createSpace(Space space, Boolean isNew) {

        if (isNew) {
            spaceRepo.save(space);
            return HttpStatus.OK;
        }

        Space spaceFromDb = spaceRepo.findById(space.getId()).get();
        updateFieldsSpace(spaceFromDb, space);

        return HttpStatus.OK;
    }

    public List<Space> getSpace(Integer userId) {

        return spaceRepo.findByUserId(userId);

    }

    public HttpStatus deleteSpace(Integer spaceId) {
        spaceRepo.delete(spaceRepo.findById(spaceId).get());
        return HttpStatus.OK;
    }

    private void updateFieldsSpace(Space spaceFromDb, Space newSpace) {
        spaceFromDb.setColor(newSpace.getColor());
        spaceFromDb.setName(newSpace.getName());
        spaceRepo.save(spaceFromDb);
    }
}

