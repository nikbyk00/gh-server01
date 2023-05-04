package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.GreenHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GreenHouseRepo extends JpaRepository<GreenHouse, Integer> {
    List<GreenHouse> findByRoomId (Integer id);

    List <GreenHouse> findByUserId(Integer userId);
}
