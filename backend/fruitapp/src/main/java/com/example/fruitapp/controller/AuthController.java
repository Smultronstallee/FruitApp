package com.example.fruitapp.controller;

import com.example.fruitapp.service.AuthService;
import com.example.fruitapp.dto.AuthResponse;
import com.example.fruitapp.dto.LoginRequest;
import com.example.fruitapp.dto.RegisterRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok(Map.of("message", "Đăng ký thành công"));

    }

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        AuthResponse res = authService.login(req);
        return ResponseEntity.ok(res);

    }

    //check email
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = authService.checkEmailExists(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    //forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {

        authService.forgotPassword(email);
        return ResponseEntity.ok(Map.of("message", "Vui lòng kiểm tra email để đặt lại mật khẩu"));


    }

    //reset password
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String
            otp, @RequestParam String newPassword) {

        authService.resetPassword(email, otp, newPassword);
        return ResponseEntity.ok(Map.of("message", "Đặt lại mật khẩu thành công"));

    }

}
