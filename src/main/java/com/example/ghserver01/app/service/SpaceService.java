package com.example.ghserver01.app.service;

import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpaceService {
    private SpaceRepo spaceRepo;
    public Space createSpaceService(Space space) {

        if (space.getId() != null) {
            Space spaceFromDb = spaceRepo.findById(space.getId()).get();

            spaceFromDb.setColor(space.getColor());
            spaceFromDb.setName(space.getName());
            spaceFromDb.setNew(false);
            spaceFromDb.setUserId(space.getUserId());

            return spaceFromDb;
        }

        Space newSpace = spaceRepo.save(space);
        newSpace.setNew(true);

        return newSpace;
    }

    public List<Space> getSpaceService(User user) {
        List<Space> space = spaceRepo.findByUserId(user.getId());
        return space;
    }

    public HttpStatus deleteSpace(Space space) {
        spaceRepo.delete(spaceRepo.findById(space.getId()).get());
        return HttpStatus.OK;
    }
}

