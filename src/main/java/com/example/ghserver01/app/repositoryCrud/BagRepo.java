package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.UserBag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepo extends JpaRepository<UserBag, Integer> {
}
