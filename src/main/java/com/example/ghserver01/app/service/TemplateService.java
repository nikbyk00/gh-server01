package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.TemplateRepo;
import com.example.ghserver01.app.storage.model.Template;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    TemplateRepo templateRepo;
    public Template getTemplate(Template template) {
        return templateRepo.findByUserId(template.getUserId());
    }
}
