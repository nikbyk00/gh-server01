package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.GreenHouseRepo;
import com.example.ghserver01.app.repositoryCrud.LandingRepo;
import com.example.ghserver01.app.repositoryCrud.TemplateRepo;
import com.example.ghserver01.app.storage.model.GreenHouse;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.storage.model.Template;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Helper.LandingHelper;
import com.example.ghserver01.app.util.Value.Constants;
import com.example.ghserver01.app.util.Value.StatusGHouse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LandingService {
    private LandingRepo landingRepo;
    private TemplateRepo templateRepo;
    private GreenHouseRepo greenHouseRepo;
    private LandingHelper landingHelper;

    public List<Landing> getHistoryLanding(Landing landing) throws BusinessException {

        List<Landing> landingFromDb = landingRepo.findByUserId(landing.getUserId());

        if (landingFromDb.isEmpty()) {
            throw new BusinessException(Constants.LANDING_NOT_FOUND);
        }

        return landingFromDb;
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

    public List<Landing> getListLanding(Landing landing) throws BusinessException {
        List<Landing> landingFromDb = landingRepo.findByGreenHouseId(landing.getGreenHouseId());

        if (landingFromDb.isEmpty()) {
            throw new BusinessException(Constants.LANDING_NOT_FOUND);
        }

        return landingFromDb;
    }

    public HttpStatus deleteLanding(Landing landing) {
        landingRepo.delete(landingRepo.findById(landing.getId()).get());
        return HttpStatus.OK;
    }
}
