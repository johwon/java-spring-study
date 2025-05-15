package com.example.myspringbootapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException {
    public BusinessException(HttpStatus httpStatus, String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public static BusinessException of(String message) {
        return new BusinessException(HttpStatus.BAD_REQUEST, message);
    }
}
