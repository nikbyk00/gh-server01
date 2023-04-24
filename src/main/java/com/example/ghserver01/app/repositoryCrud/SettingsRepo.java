package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepo extends JpaRepository<Settings, Integer> {
}
