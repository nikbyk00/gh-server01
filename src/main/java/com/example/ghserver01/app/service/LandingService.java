package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.IndicationRepo;
import com.example.ghserver01.app.repositoryCrud.LandingRepo;
import com.example.ghserver01.app.repositoryCrud.TemplateRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Indication;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.storage.model.Template;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Helper.LandingHelper;
import com.example.ghserver01.app.util.Value.Constants;
import com.example.ghserver01.app.util.Value.StatusGHouse;
import com.example.ghserver01.app.util.response.FullLandingInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LandingService {
    private LandingRepo landingRepo;
    private TemplateRepo templateRepo;
    private GreenHouseRepo greenHouseRepo;
    private LandingHelper landingHelper;
    private IndicationRepo indicationRepo;

    public List<Landing> getHistoryLanding(Integer roomId)  {
        return landingRepo.findByRoomId(roomId);
    }

    public HttpStatus createLanding(Landing landing) {

        if (landing.getIsNew()) {
            landingRepo.save(landing);
            updateStatusGreenHouse(landing);

            if (landing.getTemplate()) {
                Template template = new Template();
                templateRepo.save(landingHelper.createTemplate(template, landing));
            }

            return HttpStatus.OK;
        }

        Landing landingFromDb = landingRepo.findById(landing.getId()).get();
        landingRepo.save(landingHelper.updateLanding(landingFromDb, landing));

        GreenHouse greenHouseFromDb = greenHouseRepo.findById(landingFromDb.getGreenHouseId()).get();
        greenHouseFromDb.setLandingId(landing.getId());
        greenHouseRepo.save(greenHouseFromDb);

        return HttpStatus.OK;
    }

    private void updateStatusGreenHouse(Landing landing) {
        greenHouseRepo.findById(landing.getGreenHouseId()).get().setStatus(StatusGHouse.LANDING.toString());
    }

    public FullLandingInfo getLanding(Integer greenHouseId) {
        Landing landingFromDb = landingRepo.findByGreenHouseId(greenHouseId);
        String greenHouseName = greenHouseRepo.findById(greenHouseId).get().getName();
        Indication indication = indicationRepo.findByGreenHouseId(greenHouseId);

        return FullLandingInfo.createFullLandingInfo(landingFromDb, greenHouseName,indication);
    }

    public HttpStatus deleteLanding(Landing landing) {
        landingRepo.delete(landingRepo.findById(landing.getId()).get());
        return HttpStatus.OK;
    }
}
