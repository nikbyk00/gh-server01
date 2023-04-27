package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.LandingRepo;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.storage.model.Template;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LandingService {

    LandingRepo landingRepo;


    public HttpStatus createLanding(Landing landing) {

        if (landing.isNew() == true) {
            landing.setNew(false);
            landingRepo.save(landing);
            return HttpStatus.OK;
        }
        Landing landingFromDb = landingRepo.findById(landing.getId()).get();
        updateLanding(landingFromDb, landing);

        return null;
    }

    public Landing updateLanding(Landing landingFromDb, Landing landing) {
        landingFromDb.setEc(landing.getEc());
        landingFromDb.setCo2(landing.getCo2());
        landingFromDb.setPh(landing.getPh());
        landingFromDb.setStartWork(landing.getStartWork());
        landingFromDb.setEndWork(landing.getEndWork());
        landingFromDb.setName(landing.getName());
        landingFromDb.setDurationVentilation(landing.getDurationVentilation());
        landingFromDb.setGreenHouseId(landing.getGreenHouseId());
        landingFromDb.setTemperature(landing.getTemperature());
        landingFromDb.setIrrigationIntervals(landing.getIrrigationIntervals());
        landingFromDb.setUserId(landing.getUserId());
        landingFromDb.setTemplate(landing.isTemplate());
        landingFromDb.setLightingDuration(landing.getLightingDuration());
        landingFromDb.setVentilationIntervals(landing.getVentilationIntervals());
        landingFromDb.setWateringDuration(landing.getWateringDuration());
        landingFromDb.setLightingIntervals(landing.getLightingIntervals());
        return landingFromDb;
    }


}
