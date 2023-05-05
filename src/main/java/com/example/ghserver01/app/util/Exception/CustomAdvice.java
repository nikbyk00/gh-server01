package com.example.ghserver01.app.util.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice(annotations = CustomExceptionHandler.class)
public class CustomAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseException> handleException(BusinessException e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        ResponseException response = new ResponseException(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

