package com.example.ghserver01.app.controller;

import com.example.ghserver01.app.service.TemplateService;
import com.example.ghserver01.app.storage.model.Template;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/template")
public class TemplateController {
    private TemplateService templateService;

    @GetMapping("/get")
    public Template getTemplate(Template template) {
        return templateService.getTemplate(template);
    }
}
