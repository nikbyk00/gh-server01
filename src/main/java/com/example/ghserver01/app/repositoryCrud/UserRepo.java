package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
