package com.example.ghserver01.app.storage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String lang;
    private String topic;
    private String disablingSensor;
    private String conflicts;
    private String reminder;
    private boolean reminderMaturationDay;

}
