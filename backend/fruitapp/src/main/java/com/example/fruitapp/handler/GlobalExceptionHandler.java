package com.example.fruitapp.handler;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.fruitapp.exception.EmailAlreadyExistsException;
import com.example.fruitapp.exception.UserNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //handle email already exists
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailExist(EmailAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("timestamp", LocalDateTime.now(),"message", e.getMessage()));
    }

    //handle user not found
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("timestamp", LocalDateTime.now(), "message", e.getMessage()));
    }

    
}
