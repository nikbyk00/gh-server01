package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepo extends JpaRepository<Template, Integer> {
    List<Template> findByUserId(Integer userId);
}
