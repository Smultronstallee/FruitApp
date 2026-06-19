package com.example.fruitapp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("Người dùng không tồn tại");
    }
}
