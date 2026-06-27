package com.example.fruitapp.data.model.dto.request

data class ResetPasswordRequest(
    val email: String,
    val otp: String,
    val passwordNew: String
)
