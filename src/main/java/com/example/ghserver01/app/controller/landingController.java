package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.LandingService;
import com.example.ghserver01.app.storage.model.Landing;
import com.example.ghserver01.app.util.Exception.BusinessException;
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

    @GetMapping("/getList")
    public List<Landing> getListLanding(@RequestBody Landing landing) throws BusinessException {
        return landingService.getListLanding(landing);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteLanding(@RequestBody Landing landing) {
        return landingService.deleteLanding(landing);
    }

    @GetMapping("/historyLanding")
    public List<Landing> getHistoryLanding(@RequestBody Landing landing) throws BusinessException {
        return landingService.getHistoryLanding(landing);
    }

}
