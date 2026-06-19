package com.example.fruitapp.dto;
import lombok.Data;
import lombok.Builder;
import com.example.fruitapp.entity.Role;

@Builder
@Data
public class AuthResponse {
    private String email;
    private String token;
    private Role role;
    
}
