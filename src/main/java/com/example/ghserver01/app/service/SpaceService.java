package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.Space;
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
    private UserHelper userHelper;

    public HttpStatus createSpace(Space space) {

        if (!space.getIsNew()) {
            Space spaceFromDb = spaceRepo.findById(space.getId()).get();

            spaceFromDb.setColor(space.getColor());
            spaceFromDb.setName(space.getName());
            spaceFromDb.setUserId(space.getUserId());

            spaceRepo.save(spaceFromDb);

            return HttpStatus.OK;
        }

        spaceRepo.save(space);

        return HttpStatus.OK;
    }

    public List<Space> getSpace(Integer userId) {
        return spaceRepo.findByUserId(userId);
    }

    public HttpStatus deleteSpace(Space space) {
        spaceRepo.delete(spaceRepo.findById(space.getId()).get());
        return HttpStatus.OK;
    }
}

