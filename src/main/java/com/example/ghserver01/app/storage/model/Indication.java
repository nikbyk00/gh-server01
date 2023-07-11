package com.example.ghserver01.app.storage.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Indication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private GreenHouse greenHouse;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate time;
    private Double temperature1;
    private Double temperature2;
    private Double ec;
    private Double co2;
    private Double ph;

    public Indication(GreenHouse greenHouse) {
        this.greenHouse = greenHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indication that = (Indication) o;
        return id.equals(that.id) &&
                greenHouse.equals(that.greenHouse) &&
                time.equals(that.time) &&
                temperature1.equals(that.temperature1) &&
                temperature2.equals(that.temperature2) &&
                ec.equals(that.ec) &&
                co2.equals(that.co2) &&
                ph.equals(that.ph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, greenHouse, time, temperature1, temperature2, ec, co2, ph);
    }
}
