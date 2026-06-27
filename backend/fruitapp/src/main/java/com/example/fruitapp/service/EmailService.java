package com.example.fruitapp.service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Yêu cầu đặt lại mật khẩu - FruitApp");
        message.setText("Xin chào, \n\n "
            + "Mã OTP để đặt lại mật khẩu của bạn là:"
            + otp
            +"\n\nMã OTP này có hiệu lực trong 5 phút."
            +"\n\nTrân trọng,\nFruitApp Team."
        );
         mailSender.send(message);
    }
    
}
