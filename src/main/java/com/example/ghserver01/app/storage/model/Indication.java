package com.example.ghserver01.app.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Indication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "green_house_id")
    private GreenHouse greenHouse;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate time;
    private Double temperature1;
    private Double temperature2;
    private Double ec;
    private Double co2;
    private Double ph;

}
