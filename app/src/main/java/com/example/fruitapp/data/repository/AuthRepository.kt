package com.example.fruitapp.data.repository

import com.example.fruitapp.data.api.AuthApi
import com.example.fruitapp.data.local.TokenManager
import com.example.fruitapp.data.model.dto.response.AuthResponse
import com.example.fruitapp.data.model.dto.request.ForgotPasswordRequest
import com.example.fruitapp.data.model.dto.request.LoginRequest
import com.example.fruitapp.data.model.dto.response.MessageResponse
import com.example.fruitapp.data.model.dto.request.RegisterRequest
import com.example.fruitapp.data.model.dto.request.ResetPasswordRequest
import com.example.fruitapp.data.model.dto.request.VerifyOtpRequest
import javax.inject.Inject
import kotlin.Exception

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

    //verify otp
    suspend fun verifyOtp(
        request: VerifyOtpRequest
    ): MessageResponse{
        return authApi.verifyOtp(request)
    }

    //reset password
    suspend fun resetPassword(
        request: ResetPasswordRequest
    ): MessageResponse{
        return authApi.resetPassword(request)
    }
    // check emai
    suspend fun checkEmailExists(email: String): Boolean {
        return try {
            val response = authApi.checkEmailExists(email)
            response.isSuccessful // Giả sử API trả về 2xx nếu email tồn tại
        } catch (e: Exception) {
            // Xử lý lỗi mạng hoặc các lỗi khác, coi như email không tồn tại
            false
        }
    }
}