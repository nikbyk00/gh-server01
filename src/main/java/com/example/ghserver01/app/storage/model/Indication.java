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
public class Indication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer greenHouseId;
    private Double temperature1;
    private Double temperature2;
    private Double ec;
    private Double co2;
    private Double ph;
}
