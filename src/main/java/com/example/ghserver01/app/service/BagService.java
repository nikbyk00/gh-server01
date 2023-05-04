package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.BagRepo;
import com.example.ghserver01.app.storage.model.UserBag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BagService {

    BagRepo bagRepo;
    public HttpStatus createBag(UserBag userBag) {

        bagRepo.save(userBag);

        return HttpStatus.OK;
    }
}
