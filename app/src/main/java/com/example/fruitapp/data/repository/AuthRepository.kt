package com.example.fruitapp.data.repository

import com.example.fruitapp.data.api.AuthApi
import com.example.fruitapp.data.local.TokenManager
import com.example.fruitapp.data.model.dto.AuthResponse
import com.example.fruitapp.data.model.dto.ForgotPasswordRequest
import com.example.fruitapp.data.model.dto.LoginRequest
import com.example.fruitapp.data.model.dto.MessageResponse
import com.example.fruitapp.data.model.dto.RegisterRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager
) {
    //register
    suspend fun register(
        request: RegisterRequest
    ): MessageResponse {
        return authApi.register(request)
    }
    
    //login
    suspend fun login(
        request: LoginRequest
    ): AuthResponse {
        val response = authApi.login(request)
        tokenManager.saveToken(response.token)
        return response

    }

    //forgot password
    suspend fun forgotPassword(
        request: ForgotPasswordRequest
    ): MessageResponse{
        return authApi.forgotPassword(request)
    }
}