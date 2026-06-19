package com.example.fruitapp.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(){
        super("Email đã tồn tại");
    }
}
