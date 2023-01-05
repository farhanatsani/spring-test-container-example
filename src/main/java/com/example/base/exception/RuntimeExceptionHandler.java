package com.example.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> exception(RuntimeException exception) {
        Map<String, String> errorMessages = Map.of(
                        "status", "404",
                        "message", exception.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorMessages);
    }
}
