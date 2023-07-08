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
    private IndicationRepo indicationRepo;
    private FullLandingInfo fullLandingInfo;
    private final RoomRepo roomRepo;

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
            updateGreenHouse(landing);

            List<GreenHouse> greenHouseListFilter = new ArrayList<>();
            Integer ghId = landing.getGreenHouse().getId();
            List<Room> room = roomRepo.findAll();

            for (int i = 0; i < room.size(); i++) {
                Room roomIter = room.get(i);
                List<GreenHouse> greenHouseList = roomIter.getGreenHouseList();
                greenHouseListFilter = greenHouseList.stream().
                        filter(greenHouse -> greenHouse.getId() == ghId).
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

        updateGreenHouse(landingFromDb);

        return HttpStatus.OK;
    }

    private void updateGreenHouse(Landing landing) {
        GreenHouse greenHouse = greenHouseRepo.findById(landing.getGreenHouse().getId()).get();
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
