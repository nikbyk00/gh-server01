package com.example.ghserver01.app.storage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> roomList;

    @ManyToOne
    @JsonIgnore
    private User user;
    private String color;

    public Space(String name, String color, User user) {
        this.name = name;
        this.color = color;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomList=" + roomList +
                ", user=" + user +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Space space = (Space) o;
        return getId() != null && Objects.equals(getId(), space.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
