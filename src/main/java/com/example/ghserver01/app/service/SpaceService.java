package com.example.ghserver01.app.service;

import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.User;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Exception.CustomExceptionHandler;
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
    private UserHelper userHelper;

    public Space createSpace(Space space) {

        if (!space.getIsNew()) {
            Space spaceFromDb = spaceRepo.findById(space.getId()).get();

            spaceFromDb.setColor(space.getColor());
            spaceFromDb.setName(space.getName());
            spaceFromDb.setUserId(space.getUserId());

            spaceRepo.save(spaceFromDb);

            return spaceFromDb;
        }

        Space newSpace = spaceRepo.save(space);

        return newSpace;
    }

    public List<Space> getSpace(Space space) throws BusinessException {
        List<Space> spaceFromDb = spaceRepo.findByUserId(space.getUserId());

        if (spaceFromDb.isEmpty()) {
            throw new BusinessException(Constants.SPACE_NOT_FOUND);
        }

        return spaceFromDb;
    }

    public HttpStatus deleteSpace(Space space) {
        spaceRepo.delete(spaceRepo.findById(space.getId()).get());
        return HttpStatus.OK;
    }
}

