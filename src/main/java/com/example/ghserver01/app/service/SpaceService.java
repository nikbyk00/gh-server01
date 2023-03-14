package com.example.ghserver01.app.service;

import com.example.ghserver01.app.storage.model.Space;
import com.example.ghserver01.app.repositoryCrud.SpaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpaceService {
    private SpaceRepo spaceRepo;
    public Integer createSpaceService(Space space) {
        Space spaceSave = new Space(space.getUserId(), space.getName());
        spaceRepo.save(spaceSave);
        return spaceSave.getId();
    }

    public Optional<Space> getSpaceService(Integer id) {
        Optional<Space> space = spaceRepo.findById(id);
        return space;
    }
}

