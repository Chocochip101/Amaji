package com.chocochip.amaji.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AmajiException.class)
    public ResponseEntity<ExceptionResponse> handleConnectableException(AmajiException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getBody());
    }
}
