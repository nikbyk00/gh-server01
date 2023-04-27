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
public class Landing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String greenHouseId;
    private Integer userId;
    private String startWork;
    private String endWork;
    private Double temperature;
    private Double ec;
    private Double co2;
    private Double ph;
    private boolean template;
    private int lightingDuration;
    private int lightingIntervals;
    private int wateringDuration;
    private int irrigationIntervals;
    private int durationVentilation;
    private int ventilationIntervals;
    private boolean isNew;
}

