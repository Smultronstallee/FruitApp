package com.example.fruitapp.exception;
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Mật khẩu không hợp lệ");
    }
}
