package com.example.ghserver01.app.storage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBag {
    @Id
    private Integer id;
    private String name;
    private Integer userId;
    private String error;


}
