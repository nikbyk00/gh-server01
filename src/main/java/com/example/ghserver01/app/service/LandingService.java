package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.LandingRepo;
import com.example.ghserver01.app.repositoryCrud.TemplateRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.storage.model.Template;
import com.example.ghserver01.app.util.Exception.RequiredException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LandingService {
    LandingRepo landingRepo;
    TemplateRepo templateRepo;
    GreenHouseRepo greenHouseRepo;


    public HttpStatus createLanding(Landing landing) {

        if (landing.isNew() == true) {
            landing.setNew(false);
            landingRepo.save(landing);

            if (landing.isTemplate() == true) {
                Template template = new Template();
                templateRepo.save(createTemplate(template, landing));
            }

            return HttpStatus.OK;
        }

        Landing landingFromDb = landingRepo.findById(landing.getId()).get();
        landingRepo.save(updateLanding(landingFromDb, landing));

        GreenHouse greenHouseFromDb = greenHouseRepo.findById(landingFromDb.getGreenHouseId()).get();
        greenHouseFromDb.setLandingId(landing.getId());
        greenHouseRepo.save(greenHouseFromDb);

        return HttpStatus.OK;
    }

    private Template createTemplate(Template template, Landing landing) {
        template.setEc(landing.getEc());
        template.setCo2(landing.getCo2());
        template.setPh(landing.getPh());
        template.setName(landing.getName());
        template.setDurationVentilation(landing.getDurationVentilation());
        template.setTemperature(landing.getTemperature());
        template.setIrrigationIntervals(landing.getIrrigationIntervals());
        template.setLightingDuration(landing.getLightingDuration());
        template.setVentilationIntervals(landing.getVentilationIntervals());
        template.setWateringDuration(landing.getWateringDuration());
        template.setLightingIntervals(landing.getLightingIntervals());
        template.setUserId(landing.getUserId());

        return template;
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


    public List<Landing> getListLanding(Landing landing) { //todo
        List<Landing> landingFromDb = landingRepo.findByGreenHouseId(landing.getGreenHouseId());

        if (landingFromDb == null) {
            throw new RequiredException("посадки не существует");
        }

        return landingFromDb;
    }

    public HttpStatus deleteLanding(Landing landing) {
        landingRepo.delete(landingRepo.findById(landing.getId()).get());
        return HttpStatus.OK;
    }
}
