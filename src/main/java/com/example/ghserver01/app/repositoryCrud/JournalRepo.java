package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepo extends JpaRepository<Journal, Integer> {
}
