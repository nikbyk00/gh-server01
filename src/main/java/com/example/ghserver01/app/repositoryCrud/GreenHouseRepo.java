package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.GreenHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GreenHouseRepo extends JpaRepository<GreenHouse, Integer> {
}
