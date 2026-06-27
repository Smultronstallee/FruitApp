package com.example.fruitapp.data.model.dto.request

data class VerifyOtpRequest(
    val email: String,
    val otp: String
)