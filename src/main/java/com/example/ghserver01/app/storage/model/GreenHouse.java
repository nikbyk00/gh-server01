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
public class GreenHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String roomId;
    private String additionalTemperature;
    private String temperature;
    private String ec;
    private String co2;
    private String illuminance;
    private String liquidLevel;
    private Boolean ventilation;
    private String washingInterval;
    private String ph;

    public GreenHouse (String name, String roomId, String additionalTemperature, String temperature,
                       String ec, String co2, String illuminance,
                       String liquidLevel, Boolean ventilation, String washingInterval, String ph) {
        this.name = name;
        this.roomId = roomId;
        this.additionalTemperature = additionalTemperature;
        this.temperature = temperature;
        this.ec = ec;
        this.co2 = co2;
        this.illuminance = illuminance;
        this.liquidLevel = liquidLevel;
        this.ventilation = ventilation;
        this.washingInterval = washingInterval;
        this.ph = ph;
    }
}

