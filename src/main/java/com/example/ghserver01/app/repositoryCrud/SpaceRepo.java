package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaceRepo extends JpaRepository<Space, Integer> {
    List<Space> findByUserId (Integer id);
}
