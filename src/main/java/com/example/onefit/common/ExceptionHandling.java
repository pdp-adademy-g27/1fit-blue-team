package com.example.onefit.common;

import com.example.onefit.response.ErrorData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handeException(AuthenticationException e) {
        return ResponseEntity.status(403).body(new ErrorData(e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(new ErrorData(400, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorData(500, e.getMessage()));
    }
}
