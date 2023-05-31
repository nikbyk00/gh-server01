package com.example.ghserver01.app.util.Helper;

import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.storage.model.Template;
import org.springframework.stereotype.Service;

@Service
public class LandingHelper {
    public Template createTemplate(Template template, Landing landing) {
        template.setEc(landing.getEc());
        template.setCo2(landing.getCo2());
        template.setPh(landing.getPh());
        template.setName(landing.getName());
        template.setTemperature(landing.getTemperature());
        template.setIrrigationIntervals(landing.getIrrigationIntervals());
        template.setLightingDuration(landing.getLightingDuration());
        template.setWateringDuration(landing.getWateringDuration());
        template.setLightingIntervals(landing.getLightingIntervals());

        return template;
    }

    public Landing updateLanding(Landing landingFromDb, Landing landing) {
        landingFromDb.setEc(landing.getEc());
        landingFromDb.setCo2(landing.getCo2());
        landingFromDb.setPh(landing.getPh());
        landingFromDb.setStartWork(landing.getStartWork());
        landingFromDb.setEndWork(landing.getEndWork());
        landingFromDb.setName(landing.getName());
        landingFromDb.setGreenHouseId(landing.getGreenHouseId());
        landingFromDb.setTemperature(landing.getTemperature());
        landingFromDb.setIrrigationIntervals(landing.getIrrigationIntervals());
        landingFromDb.setTemplate(landing.getTemplate());
        landingFromDb.setLightingDuration(landing.getLightingDuration());
        landingFromDb.setWateringDuration(landing.getWateringDuration());
        landingFromDb.setLightingIntervals(landing.getLightingIntervals());
        landingFromDb.setIsNew(landing.getIsNew());
        landingFromDb.setRoomId(landing.getRoomId());

        return landingFromDb;
    }
}
