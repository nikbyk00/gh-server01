package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.GreenHouse;
import org.springframework.data.repository.CrudRepository;

public interface GreenHouseRepo extends CrudRepository<GreenHouse, Integer> {
}
