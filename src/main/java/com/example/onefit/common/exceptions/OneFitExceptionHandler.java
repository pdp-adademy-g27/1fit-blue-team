package com.example.onefit.common.exceptions;


import com.example.onefit.common.response.CommonResponse;
import com.example.onefit.response.ErrorData;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class OneFitExceptionHandler {
    @ExceptionHandler(OtpException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResponse handleEarlyResentException(OtpException e) {
        return new CommonResponse(e.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handeException(AuthenticationException e) {
        return ResponseEntity.status(403).body(new ErrorData(e.getMessage()));
    }



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(new ErrorData(400, e.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException e) {
        return ResponseEntity.badRequest().body(new ErrorData(400, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new ErrorData(500, e.getMessage()));
    }
}
