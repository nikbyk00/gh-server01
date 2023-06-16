package com.example.ghserver01.app.storage.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GreenHouse {
    @Id
    private Integer id;
    private String name;
    private String status;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "landing_id")
    private Landing landing;
    @OneToOne
    @JoinColumn(name = "indication_id")
    private Indication indication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GreenHouse that = (GreenHouse) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
