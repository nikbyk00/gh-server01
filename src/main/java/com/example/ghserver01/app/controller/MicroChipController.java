package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.MicroChipService;
import com.example.ghserver01.app.storage.model.Indication;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.response.MicroChip;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microChip")
@AllArgsConstructor
public class MicroChipController {
    private MicroChipService microChipService;

    @PostMapping("/create")
    public HttpStatus createMicroChip(@RequestParam Integer id) throws BusinessException {
        return microChipService.create(id);
    }

    @PostMapping("/processParameters")
    public MicroChip checkMChip(@RequestBody Indication indication) {
        return microChipService.compareIndicators(indication);
    }

}
