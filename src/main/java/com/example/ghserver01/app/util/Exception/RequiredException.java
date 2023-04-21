package com.example.ghserver01.app.util.Exception;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class RequiredException extends RuntimeException {
    public RequiredException(String message) {
        super(message);
    }
}
