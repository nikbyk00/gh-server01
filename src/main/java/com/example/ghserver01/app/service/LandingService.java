package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.*;
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
      // List<GreenHouse> gHouseFromDb = greenHouseRepo.findByRoomId(roomId);
//       List <Landing> landing =
//               gHouseFromDb.
//               stream().
//               peek(s -> landingRepo.findByGreenHouseId(s.getId())).
//               collect(Collectors.toList());

        return null;
    }

    public HttpStatus createLanding(Landing landing, Boolean isNew, Boolean creatingTemplate) {

        if (isNew) {
            landingRepo.save(landing);
            updateStatusGreenHouse(landing);
            updateGreenHouseId(landing);

            if (creatingTemplate) {
                Template template = new Template();
                templateRepo.save(landingHelper.createTemplate(template, landing));
            }

            return HttpStatus.OK;
        }

        Landing landingFromDb = landingRepo.findById(landing.getId()).get();
        landingRepo.save(landingHelper.updateLanding(landingFromDb, landing));

        updateGreenHouseId(landingFromDb);

        return HttpStatus.OK;
    }

    private void updateStatusGreenHouse(Landing landing) {
        greenHouseRepo.findById(landing.getGreenHouse().getId()).get().setStatus(StatusGHouse.LANDING.toString());
    }

    private void updateGreenHouseId(Landing landing) {
        GreenHouse greenHouseFromDb = greenHouseRepo.findById(landing.getGreenHouse().getId()).get();
        greenHouseFromDb.setLanding(landing);
        greenHouseRepo.save(greenHouseFromDb);
    }

    public FullLandingInfo getLanding(Integer greenHouseId) {
        GreenHouse greenHouse = greenHouseRepo.findById(greenHouseId).get();
        Landing landingFromDb = landingRepo.findById(greenHouse.getLanding().getId()).get();
        String greenHouseName = greenHouseRepo.findById(greenHouseId).get().getName();
        Indication indication = indicationRepo.findByGreenHouseId(greenHouseId);

        return FullLandingInfo.createFullLandingInfo(landingFromDb, greenHouseName,indication);
    }

    public HttpStatus deleteLanding(Landing landing) {
        landingRepo.delete(landingRepo.findById(landing.getId()).get());
        return HttpStatus.OK;
    }
}
