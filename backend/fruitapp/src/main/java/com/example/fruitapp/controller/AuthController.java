package com.example.fruitapp.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fruitapp.dto.request.ForgotPasswordRequest;
import com.example.fruitapp.dto.request.LoginRequest;
import com.example.fruitapp.dto.request.RegisterRequest;
import com.example.fruitapp.dto.request.ResetPasswordRequest;
import com.example.fruitapp.dto.request.VerifyOtpRequest;
import com.example.fruitapp.dto.response.AuthResponse;
import com.example.fruitapp.service.AuthService;
import com.example.fruitapp.service.FacebookService;
import com.example.fruitapp.service.GoogleService;
import com.example.fruitapp.dto.request.GgLoginRequest;
import com.example.fruitapp.entity.Provider;
import com.example.fruitapp.dto.request.FbLoginRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final GoogleService ggService;
    private final FacebookService facebookService;


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

    //gg
    @PostMapping("/google")
public ResponseEntity<?> googleLogin(
        @RequestBody GgLoginRequest request
){

    AuthResponse googleUser =
            ggService.verify(request.getIdToken());


    return ResponseEntity.ok(
            authService.socialLogin(
                    googleUser.getEmail(),
                    googleUser.getUserName(),
                    Provider.GOOGLE,
                    googleUser.getSocialId()
            )
    );
}
//fb
@PostMapping("/facebook")
public ResponseEntity<?> facebookLogin(@RequestBody FbLoginRequest request) {
    try {
        AuthResponse fbUser = facebookService.verify(request.getAccessToken());

        return ResponseEntity.ok(
            authService.socialLogin(
                fbUser.getEmail(),
                fbUser.getUserName(),
                Provider.FACEBOOK,
                fbUser.getSocialId()
            )
        );
    } catch (Exception e) {
        e.printStackTrace(); // In lỗi ra console
        throw e;
    }
}
}
