package com.example.fruitapp.exception;

public class OtpExpiredException extends RuntimeException {
    public OtpExpiredException(){
        super("OTP đã hết hạn sử dụng. Vui lòng yêu cầu lại.");
    }
}