package com.chocochip.amaji.security.exception;

import com.chocochip.amaji.exception.ErrorType;
import com.chocochip.amaji.exception.ExceptionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AmajiSecurityException extends Exception{
    private static final ObjectMapper JSON_CONVERTER = new ObjectMapper();


    public AmajiSecurityException(String message) {
        super(message);
    }

    public AmajiSecurityException(ErrorType errorType) {
        this(generateExceptionMessage(errorType));
    }

    private static String generateExceptionMessage(ErrorType errorType) {
        try {
            return JSON_CONVERTER.writeValueAsString(new ExceptionResponse(errorType.getErrorCode(), errorType.getMessage()));
        } catch (JsonProcessingException e) {
            return "JWT 인증 오류";
        }
    }
}
