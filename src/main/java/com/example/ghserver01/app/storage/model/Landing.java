package com.example.ghserver01.app.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private Integer greenHouseId;
    private Integer roomId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startWork;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endWork;
    private Double temperature;
    private Double ec;
    private Double co2;
    private Double ph;
    private Boolean template;
    private Integer lightingDuration;
    private Integer lightingIntervals;
    private Integer wateringDuration;
    private Integer irrigationIntervals;
    private Boolean isNew;
}

