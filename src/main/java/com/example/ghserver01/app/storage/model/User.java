package com.example.ghserver01.app.storage.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String activationCode;
    private boolean activate = false;
    private String theme = "white";
    private String lang = "Russian";

}
