package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicationRepo extends JpaRepository<Indication, Integer> {
    Indication findByGreenHouseId(Integer greenHouseId);
}
