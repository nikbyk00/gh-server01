package com.example.ghserver01.app.storage.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    private String name;
    private Double temperature;
    private Double ec;
    private Double co2;
    private Double ph;
    private Integer lightingDuration;
    private Integer lightingIntervals;
    private Integer wateringDuration;
    private Integer irrigationIntervals;
}
