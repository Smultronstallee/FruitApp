package com.example.fruitapp.exception;

public class SocialAccountException extends RuntimeException {
    public SocialAccountException() {
        super("Tài khoản này được đăng ký bằng mạng xã hội. Vui lòng đăng nhập qua mạng xã hội tương ứng.");
    }
}