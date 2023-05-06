package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.TemplateService;
import com.example.ghserver01.app.storage.model.Template;
import com.example.ghserver01.app.util.Exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/template")
public class TemplateController {
    private TemplateService templateService;

    @GetMapping("/get")
    public List<Template> getTemplate(@RequestBody Template template) throws BusinessException {
        return templateService.getTemplate(template);
    }

    @DeleteMapping("/delete")
    public HttpStatus deleteTemplate(@RequestBody Template template) {
        return templateService.deleteTemplate(template);
    }
}
