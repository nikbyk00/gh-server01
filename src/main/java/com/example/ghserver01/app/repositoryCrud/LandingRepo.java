package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Landing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LandingRepo extends JpaRepository<Landing, Integer> {
    List<Landing> findByRoomId(Integer id);
    Landing findByGreenHouseId(Integer greenHouseId);
}
