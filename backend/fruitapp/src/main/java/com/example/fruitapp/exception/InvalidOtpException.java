package com.example.fruitapp.exception;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException() {
        super("Mã OTP không hợp lệ hoặc không tồn tại.");
    }
}