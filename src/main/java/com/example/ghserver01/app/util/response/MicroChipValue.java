package com.example.ghserver01.app.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MicroChipValue {
    private boolean heat;
    private boolean vent;
    private boolean pH;
    private boolean ec;
    private boolean light;
    private boolean watering;

}



