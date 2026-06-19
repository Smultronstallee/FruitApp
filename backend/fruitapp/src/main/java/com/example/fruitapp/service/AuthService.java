package com.example.fruitapp.service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fruitapp.dto.AuthResponse;
import com.example.fruitapp.dto.LoginRequest;
import com.example.fruitapp.dto.RegisterRequest;
import com.example.fruitapp.entity.User;
import com.example.fruitapp.entity.Provider;
import com.example.fruitapp.exception.EmailAlreadyExistsException;
import com.example.fruitapp.exception.InvalidPasswordException;
import com.example.fruitapp.exception.InvalidOtpException;
import com.example.fruitapp.exception.SocialAccountException;
import com.example.fruitapp.exception.OtpExpiredException;
import com.example.fruitapp.exception.UserNotFoundException;
import com.example.fruitapp.repository.AuthRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    //register
    @Transactional
    public void register(RegisterRequest req){

        if(authRepo.existsByEmail(req.getEmail())){
            throw new EmailAlreadyExistsException();
        }

        User u = User.builder()
                .email(req.getEmail())
                .userName(req.getUserName())
                .password(passwordEncoder.encode(req.getPassword()))
                .provider(Provider.LOCAL)
                .failedLoginAttempts(0)
                .build();

        authRepo.save(u);
    }

    //login
    public AuthResponse login(LoginRequest req){
        User u = authRepo.findByEmail(req.getEmail())
                .orElseThrow(UserNotFoundException::new);
        
        if(u.getProvider() != Provider.LOCAL){
            throw new SocialAccountException();
        }

        if(!passwordEncoder.matches(req.getPassword(), u.getPassword())){
            throw new InvalidPasswordException();
        }

        String token = jwtService.generateToken(u.getEmail());
        return AuthResponse.builder()
                .email(u.getEmail())
                .token(token)
                .build();
    
}

    // forgot password
    @Transactional
    public void forgotPassword(String email){
        User u = authRepo.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);

        String otp = String.valueOf(ThreadLocalRandom
            .current()
            .nextInt(100000, 1000000));
            u.setResetToken(otp);
            u.setResetTokenExpiry(LocalDateTime.now().plusMinutes(5)
        );
        authRepo.save(u);
        emailService.sendResetPasswordEmail(u.getEmail(), otp);
}

    //reset password
    @Transactional
    public void resetPassword(String email, String otp, String newPassword){
        User u = authRepo.findByEmail(email)
            .orElseThrow(UserNotFoundException::new);


        validateOtp(u, otp);

        u.setPassword(passwordEncoder.encode(newPassword));
        u.setResetToken(null);
        u.setResetTokenExpiry(null);
        authRepo.save(u);
    }

    //validate otp
    private void validateOtp(User u, String otp){
        if(u.getResetToken()==null){
            throw new InvalidOtpException();
        }
        
        if(!otp.equals(u.getResetToken())){
            throw new InvalidOtpException();
        }

        if(u.getResetTokenExpiry() !=null && u.getResetTokenExpiry().isBefore(LocalDateTime.now())){
            throw new OtpExpiredException();
        }
    }

    //check email
    public boolean checkEmailExists(String email){
        return authRepo.existsByEmail(email);
    }

}
