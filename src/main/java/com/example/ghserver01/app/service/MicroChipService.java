package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.IndicationRepo;
import com.example.ghserver01.app.repositoryCrud.LandingRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Indication;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Value.Constants;
import com.example.ghserver01.app.util.Value.StatusGHouse;
import com.example.ghserver01.app.util.response.MicroChipValue;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MicroChipService {

    private IndicationRepo indicationRepo;
    private LandingRepo landingRepo;
    private GreenHouseRepo greenHouseRepo;

    public MicroChipValue compareIndicators(Indication indication, Integer greenHouseId) { //indecator save??
        GreenHouse greenHouseFromDb = greenHouseRepo.findById(greenHouseId).get();
        greenHouseFromDb.getIndication().add(indication);
        greenHouseRepo.save(greenHouseFromDb);
        Landing landing = landingRepo.findById(greenHouseFromDb.getLanding().getId()).get();

        return compareParam(landing, indication, new MicroChipValue());
    }

    private MicroChipValue compareParam(Landing landing, Indication indication, MicroChipValue microChip) {

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

    public HttpStatus create(Integer id) throws BusinessException {

        if (!greenHouseRepo.findById(id).isEmpty()){
            throw new BusinessException(Constants.GREEN_HOUSE_ALREADY_EXISTS);
        }

        GreenHouse greenHouse = greenHouseRepo.save(new GreenHouse(id, StatusGHouse.EMPTY.toString()));
        Indication indication = indicationRepo.save(new Indication(greenHouse));
        greenHouse.getIndication().add(indication);
        greenHouseRepo.save(greenHouse);

        return HttpStatus.OK;
    }
}
