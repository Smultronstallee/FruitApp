package com.example.fruitapp.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fruitapp.dto.AuthResponse;
import com.example.fruitapp.dto.ForgotPasswordRequest;
import com.example.fruitapp.dto.LoginRequest;
import com.example.fruitapp.dto.RegisterRequest;
import com.example.fruitapp.dto.ResetPasswordRequest;
import com.example.fruitapp.dto.VerifyOtpRequest;
import com.example.fruitapp.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok(Map.of("message", "Đăng ký thành công"));

    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        AuthResponse res = authService.login(req);
        return ResponseEntity.ok(res);

    }

    // check email
    @GetMapping("/check-email")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = authService.checkEmailExists(email);
        return ResponseEntity.ok(Map.of("exists", exists));
    }

    // forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {

        authService.forgotPassword(request);
        return ResponseEntity.ok(Map.of("message", "Vui lòng kiểm tra email để đặt lại mật khẩu"));

    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest req) {

        authService.verifyOtp(req.getEmail(), req.getOtp());

        return ResponseEntity.ok(Map.of(
                "message", "OTP hợp lệ"));
    }

    // reset password
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ResetPasswordRequest request) {

        authService.resetPassword(
                request.getEmail(),
                request.getOtp(),
                request.getPasswordNew());

        return ResponseEntity.ok(
                Map.of("message", "Đặt lại mật khẩu thành công"));
    }

}
