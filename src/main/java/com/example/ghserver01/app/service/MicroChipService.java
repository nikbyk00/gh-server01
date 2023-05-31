package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.IndicationRepo;
import com.example.ghserver01.app.repositoryCrud.LandingRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Indication;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.util.Value.StatusGHouse;
import com.example.ghserver01.app.util.response.MicroChip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MicroChipService {

    private IndicationRepo indicationRepo;
    private LandingRepo landingRepo;
    private GreenHouseRepo greenHouseRepo;

    public MicroChip compareIndicators(Indication indication) {
        Landing landing = landingRepo.findByGreenHouseId(indication.getGreenHouseId());
        Indication indicationFromDb = indicationRepo.findByGreenHouseId(indication.getGreenHouseId());

        updateIndication(indicationFromDb, indication);
        return compareParam(landing, indication, MicroChip.getResponseMicroChip());
    }

    private MicroChip compareParam(Landing landing, Indication indication, MicroChip microChip) {

        double arithmeticMean = (indication.getTemperature1() + indication.getTemperature2()) / 2;

        if (arithmeticMean < landing.getTemperature()) {
            microChip.setHeat(true);
        }
        if (indication.getPh() < landing.getPh()) {
            microChip.setPH(true);
        }
        if (indication.getEc() < landing.getEc()) {
            microChip.setEc(true);
        }
        if (indication.getCo2() < landing.getCo2()) {
            microChip.setVent(true);
        }

        return microChip;
    }

    private void updateIndication(Indication oldIndication, Indication indication) {
        oldIndication.setTemperature1(indication.getTemperature1());
        oldIndication.setTemperature2(indication.getTemperature2());
        oldIndication.setEc(indication.getEc());
        oldIndication.setPh(indication.getPh());
        oldIndication.setCo2(indication.getCo2());

        indicationRepo.save(oldIndication);
    }

    public void create(Integer id) {
        GreenHouse greenHouse = new GreenHouse();
        greenHouse.setStatus(StatusGHouse.EMPTY.toString());
        greenHouse.setId(id);
        greenHouseRepo.save(greenHouse);
        Indication indication = new Indication();
        indication.setGreenHouseId(greenHouse.getId());
        indicationRepo.save(indication);
    }
}
