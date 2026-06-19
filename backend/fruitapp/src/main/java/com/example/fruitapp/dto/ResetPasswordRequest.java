package com.example.fruitapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String otp;

    @NotBlank
    @Size(min=6)
    private String newPassword;
}
