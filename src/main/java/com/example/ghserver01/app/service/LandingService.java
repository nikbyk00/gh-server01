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

    public List<Landing> getHistoryLanding(Integer userId)  {
        return landingRepo.findByUserId(userId);
    }

    public Landing createLanding(Landing landing) {

        if (landing.getIsNew()) {
            Landing newLanding = landingRepo.save(landing);
            updateStatusGreenHouse(landing);

            if (landing.getTemplate()) { //todo
                Template template = new Template();
                templateRepo.save(landingHelper.createTemplate(template, landing));
            }

            return newLanding;
        }

        Landing landingFromDb = landingRepo.findById(landing.getId()).get();
        landingRepo.save(landingHelper.updateLanding(landingFromDb, landing));

        GreenHouse greenHouseFromDb = greenHouseRepo.findById(landingFromDb.getGreenHouseId()).get();
        greenHouseFromDb.setLandingId(landing.getId());
        greenHouseRepo.save(greenHouseFromDb);

        return landingFromDb;
    }

    private void updateStatusGreenHouse(Landing landing) {
        greenHouseRepo.findById(landing.getGreenHouseId()).get().setStatus(StatusGHouse.LANDING.toString());
    }

    public List<Landing> getListLanding(Integer greenHouseId) {
        return landingRepo.findByGreenHouseId(greenHouseId);
    }

    public HttpStatus deleteLanding(Landing landing) {
        landingRepo.delete(landingRepo.findById(landing.getId()).get());
        return HttpStatus.OK;
    }
}
