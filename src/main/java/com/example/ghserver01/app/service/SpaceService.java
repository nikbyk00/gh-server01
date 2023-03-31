package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.UserRepo;
import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import com.example.ghserver01.app.storage.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SpaceService {
    private SpaceRepo spaceRepo;
    private UserRepo userRepo;
    public Integer createSpaceService(Space space) {
        Space spaceSave = new Space(space.getName(), space.getUserId());
        spaceRepo.save(spaceSave);
        updateUserSpaceId( userRepo.findById(space.getUserId()).get(), spaceSave.getId() );
        return spaceSave.getId();
    }

    public void updateUserSpaceId (User user, Integer spaceId) {
        user.setSpaceId(spaceId);
    }

    public Optional<Space> getSpaceService(Integer id) {
        Optional<Space> space = spaceRepo.findById(id);
        return space;
    }
}

