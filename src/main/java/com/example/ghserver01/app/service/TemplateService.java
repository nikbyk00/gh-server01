package com.example.ghserver01.app.service;

import com.example.ghserver01.app.repositoryCrud.TemplateRepo;
import com.example.ghserver01.app.storage.model.Template;
import com.example.ghserver01.app.util.Exception.BusinessException;
import com.example.ghserver01.app.util.Value.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    private TemplateRepo templateRepo;

    public List<Template> getTemplate(Template template) throws BusinessException {
        List<Template> templateFromDb = templateRepo.findByUserId(template.getUserId());

        if(templateFromDb.isEmpty()) {
            throw new BusinessException(Constants.TEMPLATE_NOT_FOUND);
        }

        return templateFromDb;
    }

    public HttpStatus deleteTemplate(Template template) {
        templateRepo.delete(template);
        return HttpStatus.OK;
    }
}
