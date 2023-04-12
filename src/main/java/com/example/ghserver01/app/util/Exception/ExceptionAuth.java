package com.example.ghserver01.app.util.Exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ExceptionAuth extends Exception {
    public ExceptionAuth(String message) {
        super(message);
    }
}
