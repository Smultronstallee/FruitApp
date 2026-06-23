package com.example.fruitapp.data.api

import com.example.fruitapp.data.model.dto.AuthResponse
import com.example.fruitapp.data.model.dto.ForgotPasswordRequest
import com.example.fruitapp.data.model.dto.LoginRequest
import com.example.fruitapp.data.model.dto.MessageResponse
import com.example.fruitapp.data.model.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface AuthApi {

    //register
   @POST("api/auth/register")
   suspend fun register(@Body request: RegisterRequest): MessageResponse

   //login
   @POST("api/auth/login")
   suspend fun login(@Body request: LoginRequest): AuthResponse

    //forgot password
    @POST("api/auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): MessageResponse
}