package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpaceRepo extends JpaRepository<Space, Integer> {
}
