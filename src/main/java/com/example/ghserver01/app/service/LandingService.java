package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.*;
import com.example.ghserver01.app.storage.model.*;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Helper.LandingHelper;
import com.example.ghserver01.app.util.Value.Constants;
import com.example.ghserver01.app.util.Value.StatusGHouse;
import com.example.ghserver01.app.util.response.FullLandingInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LandingService {
    private LandingRepo landingRepo;
    private TemplateRepo templateRepo;
    private GreenHouseRepo greenHouseRepo;
    private LandingHelper landingHelper;

    private FullLandingInfo fullLandingInfo;
    private final RoomRepo roomRepo;

    public List<Landing> getHistoryLanding(Integer roomId)  {
        return roomRepo.findById(roomId).get().getLandingList();
    }

    public HttpStatus createLanding(Landing landing, Boolean isNew, Boolean creatingTemplate, Integer greenHouseId) {

        if (isNew) {
            landingRepo.save(landing);
            updateGreenHouse(landing, greenHouseId);

            List<GreenHouse> greenHouseListFilter;
            List<Room> room = roomRepo.findAll();

            for (int i = 0; i < room.size(); i++) {
                Room roomIter = room.get(i);
                List<GreenHouse> greenHouseList = roomIter.getGreenHouseList();
                greenHouseListFilter = greenHouseList.stream().
                        filter(greenHouse -> greenHouse.getId() == greenHouseId).
                        collect(Collectors.toList());

                if (!greenHouseListFilter.isEmpty()) {
                    Room roomFromDb = roomRepo.findById(roomIter.getId()).get();
                    roomFromDb.getLandingList().add(landing);
                    roomRepo.save(roomFromDb);
                }
            }

            if (creatingTemplate) {
                Template template = new Template();
                templateRepo.save(landingHelper.createTemplate(template, landing));
            }

            return HttpStatus.OK;
        }

        Landing landingFromDb = landingRepo.findById(landing.getId()).get();
        landingRepo.save(landingHelper.updateLanding(landingFromDb, landing));

        updateGreenHouse(landingFromDb, greenHouseId);

        return HttpStatus.OK;
    }

    private void updateGreenHouse(Landing landing, Integer greenHouseId) {
        GreenHouse greenHouse = greenHouseRepo.findById(greenHouseId).get();
        greenHouse.setStatus(StatusGHouse.LANDING.toString());
        greenHouse.setLanding(landing);
        greenHouseRepo.save(greenHouse);
    }

    public GreenHouse getLanding(Integer greenHouseId) {
        GreenHouse greenHouse = greenHouseRepo.findById(greenHouseId).get();
        return greenHouse;
    }

    public HttpStatus deleteLanding(Landing landing) {
        landingRepo.delete(landingRepo.findById(landing.getId()).get());
        return HttpStatus.OK;
    }
}
