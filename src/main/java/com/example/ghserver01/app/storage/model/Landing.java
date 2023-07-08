package com.example.ghserver01.app.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Landing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "green_house_id")
    private GreenHouse greenHouse;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startWork;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endWork;
    private Double temperature;
    private Double ec;
    private Double co2;
    private Double ph;
    private Integer lightingDuration;
    private Integer lightingIntervals;
    private Integer wateringDuration;
    private Integer irrigationIntervals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Landing landing = (Landing) o;
        return getId() != null && Objects.equals(getId(), landing.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

