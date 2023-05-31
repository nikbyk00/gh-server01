package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.LandingService;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.util.response.FullLandingInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/landing")
@AllArgsConstructor
public class landingController {
    private LandingService landingService;

    @PostMapping("/create")
    public HttpStatus createLanding(@RequestBody Landing landing) {
        return landingService.createLanding(landing);
    }

    @GetMapping("/getLanding")
    public FullLandingInfo getLanding(@RequestParam Integer greenHouseId) {
        return landingService.getLanding(greenHouseId);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteLanding(@RequestBody Landing landing) {
        return landingService.deleteLanding(landing);
    }

    @GetMapping("/historyLanding")
    public List<Landing> getHistoryLanding(@RequestParam Integer roomId) {
        return landingService.getHistoryLanding(roomId);
    }

}
