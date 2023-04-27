package com.example.ghserver01.app.repositoryCrud;

import com.example.ghserver01.app.storage.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepo extends JpaRepository<Calendar, Integer> {
}
