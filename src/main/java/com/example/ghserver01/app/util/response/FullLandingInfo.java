package com.example.ghserver01.app.util.response;

import com.example.ghserver01.app.storage.model.Indication;
import com.example.ghserver01.app.storage.model.Landing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullLandingInfo {
    private Landing landing;
    private String greenHouseName;
    private Indication indication;
    public static FullLandingInfo createFullLandingInfo(Landing landing, String greenHouseName, Indication indication) {
        return new FullLandingInfo(landing, greenHouseName, indication);
    }
}
