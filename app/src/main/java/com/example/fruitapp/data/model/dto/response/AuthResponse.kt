package com.example.fruitapp.data.model.dto.response

data class AuthResponse(
    val email: String,
    val token: String,
    val newPassword: String
)