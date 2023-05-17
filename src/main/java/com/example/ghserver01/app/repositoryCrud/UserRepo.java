package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
