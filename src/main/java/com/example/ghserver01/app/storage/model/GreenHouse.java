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
    private Integer id;
    private Integer roomId;
    private Integer userId;
    private String name;
    private Integer landingId;
    private String status;
    private Boolean isNew = false;
}
