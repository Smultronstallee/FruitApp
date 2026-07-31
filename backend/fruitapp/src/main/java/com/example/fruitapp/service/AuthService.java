package com.example.fruitapp.service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fruitapp.dto.request.ForgotPasswordRequest;
import com.example.fruitapp.dto.request.LoginRequest;
import com.example.fruitapp.dto.request.RegisterRequest;
import com.example.fruitapp.dto.response.AuthResponse;
import com.example.fruitapp.entity.Provider;
import com.example.fruitapp.entity.User;
import com.example.fruitapp.exception.EmailAlreadyExistsException;
import com.example.fruitapp.exception.InvalidOtpException;
import com.example.fruitapp.exception.InvalidPasswordException;
import com.example.fruitapp.exception.OtpExpiredException;
import com.example.fruitapp.exception.RoleNotFoundException;
import com.example.fruitapp.exception.SocialAccountException;
import com.example.fruitapp.exception.UserNotFoundException;
import com.example.fruitapp.repository.AuthRepository;
import com.example.fruitapp.repository.RoleRepository;
import com.example.fruitapp.entity.RoleType;
import com.example.fruitapp.entity.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final RoleRepository roleRepo;

    // register
    @Transactional
    public void register(RegisterRequest req) {

        if (authRepo.existsByEmail(req.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        createNewUser(
                req.getEmail(),
                req.getUserName(),
                Provider.LOCAL,
                passwordEncoder.encode(req.getPassword()),
                null,
                null); // Thêm null cho socialId
    }

    // login
    public AuthResponse login(LoginRequest req) {
        User u = authRepo.findByEmail(req.getEmail())
                .orElseThrow(UserNotFoundException::new);

        if (u.getPassword() == null) {
            throw new SocialAccountException(
                    "Tài khoản này chưa thiết lập mật khẩu.");
        }

        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            u.setFailedLoginAttempts(u.getFailedLoginAttempts() + 1);
            authRepo.save(u);
            throw new InvalidPasswordException();
        }

        // Reset failed attempts on successful login
        u.setFailedLoginAttempts(0);
        authRepo.save(u);

        return createAuthResponse(u);
    }

    // forgot password
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) {
        User u = authRepo.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        String otp = String.valueOf(ThreadLocalRandom
                .current()
                .nextInt(100000, 1000000));
        u.setResetToken(otp);
        u.setResetTokenExpiry(LocalDateTime.now().plusMinutes(5));
        authRepo.save(u);
        emailService.sendResetPasswordEmail(u.getEmail(), otp);
    }

    // reset password
    @Transactional
    public void resetPassword(String email, String otp, String passwordNew) {
        User u = authRepo.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        validateOtp(u, otp);

        u.setPassword(passwordEncoder.encode(passwordNew));
        u.setResetToken(null);
        u.setResetTokenExpiry(null);
        authRepo.save(u);
    }

    // validate otp
    private void validateOtp(User u, String otp) {
        if (u.getResetToken() == null || !otp.equals(u.getResetToken())) {
            throw new InvalidOtpException();
        }

        if (u.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new OtpExpiredException();
        }
    }

    // verify otp
    public void verifyOtp(String email, String otp) {
        User u = authRepo.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        validateOtp(u, otp);
    }

    // check email
    public boolean checkEmailExists(String email) {
        return authRepo.existsByEmail(email);
    }

    private AuthResponse createAuthResponse(User u) {
        return AuthResponse.builder()
                .email(u.getEmail())
                .userName(u.getUserName())
                .token(jwtService.generateToken(u.getEmail()))
                .role(u.getRole())
                .build();
    }

    public AuthResponse socialLogin(
            String email,
            String userName,
            Provider provider,
            String socialId) {

        User user = authRepo.findByEmail(email)
                .orElse(null);

        // Chưa có tài khoản -> tạo mới
        if (user == null) {
            user = createNewUser(email, userName, provider, null, null, socialId);
        }
        // Đã có tài khoản
        else {

            // Email đã đăng ký bằng phương thức khác
            if (provider == Provider.GOOGLE) {

                if (user.getGoogleId() == null) {
                    user.setGoogleId(socialId);
                    authRepo.save(user);
                }

            } else if (provider == Provider.FACEBOOK) {

                if (user.getFacebookId() == null) {
                    user.setFacebookId(socialId);
                    authRepo.save(user);
                }
            }

        }

        return createAuthResponse(user);
    }

    private User createNewUser(String email, String userName, Provider provider, String password, String avatarUrl, String socialId) {
        Role userRole = roleRepo.findByName(RoleType.USER)
                .orElseThrow(() -> new RoleNotFoundException("Không tìm thấy vai trò mặc định 'USER' trong hệ thống."));

        User newUser = User.builder()
                .email(email)
                .userName(userName)
                .provider(provider)
                .password(password) // Sẽ là null cho social login
                .avatarUrl(avatarUrl)
                .role(userRole)
                .failedLoginAttempts(0)
                .build();
        
        if (provider == Provider.GOOGLE) {
            newUser.setGoogleId(socialId);
        } else if (provider == Provider.FACEBOOK) {
            newUser.setFacebookId(socialId);
        }

        return authRepo.save(newUser);
    }

}
