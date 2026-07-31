package com.example.fruitapp.dto.response;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.example.fruitapp.entity.Role;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String email;
    private String token;
    private String userName;
    private Role role;
    private String socialId;
    
}
