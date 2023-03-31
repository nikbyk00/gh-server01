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
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userId;
    private String lang;
    private String topic;
    private String disablingSensor;
    private String conflicts;
    private String reminder;
    private boolean reminderMaturationDay;
    public Settings (String userId, String lang, String topic, String disablingSensor,
                    String conflicts, String reminder, boolean reminderMaturationDay) {
        this.userId = userId;
        this.lang = lang;
        this.topic = topic;
        this.disablingSensor = disablingSensor;
        this.conflicts = conflicts;
        this.reminder = reminder;
        this.reminderMaturationDay = reminderMaturationDay;
    }

}
