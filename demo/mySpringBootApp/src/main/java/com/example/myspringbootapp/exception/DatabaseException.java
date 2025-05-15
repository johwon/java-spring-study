package com.example.myspringbootapp.exception;

import org.springframework.http.HttpStatus;

public class DatabaseException extends ApiException{

    public DatabaseException(HttpStatus httpStatus, String message) {
        super(HttpStatus.NOT_FOUND, message);
    }

    public static DatabaseException of(String message){
        return new DatabaseException(HttpStatus.NOT_FOUND, message);
    }
}
