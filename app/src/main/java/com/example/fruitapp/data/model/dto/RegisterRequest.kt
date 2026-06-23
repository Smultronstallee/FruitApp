package com.example.fruitapp.data.model.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val userName: String,
    val email: String,
    val password: String,
)
