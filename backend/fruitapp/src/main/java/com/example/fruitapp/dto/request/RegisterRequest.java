package com.example.fruitapp.dto.request;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String userName;

}
