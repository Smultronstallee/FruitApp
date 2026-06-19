package com.example.fruitapp.dto;
import lombok.Data;
import lombok.Builder;


@Data
@Builder
public class ApiResponse {
    private boolean success;
    private String message;
}
