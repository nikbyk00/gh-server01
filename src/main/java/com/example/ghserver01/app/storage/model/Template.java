package com.example.ghserver01.app.storage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String name;
    private Double temperature;
    private Double ec;
    private Double co2;
    private Double ph;
    private int lightingDuration;
    private int lightingIntervals;
    private int wateringDuration;
    private int irrigationIntervals;
    private int durationVentilation;
    private int ventilationIntervals;
}
