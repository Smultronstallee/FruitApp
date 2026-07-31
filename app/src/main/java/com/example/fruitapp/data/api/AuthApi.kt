package com.example.fruitapp.data.api

import com.example.fruitapp.data.model.dto.request.FbLoginRequest
import com.example.fruitapp.data.model.dto.response.AuthResponse
import com.example.fruitapp.data.model.dto.request.ForgotPasswordRequest
import com.example.fruitapp.data.model.dto.request.GoogleLoginRequest
import com.example.fruitapp.data.model.dto.request.LoginRequest
import com.example.fruitapp.data.model.dto.response.MessageResponse
import com.example.fruitapp.data.model.dto.request.RegisterRequest
import com.example.fruitapp.data.model.dto.request.ResetPasswordRequest
import com.example.fruitapp.data.model.dto.request.VerifyOtpRequest
import retrofit2.Response
import retrofit2.http.Body
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

    //verify otp
    @POST("api/auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): MessageResponse

    //reset password
    @POST("api/auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): MessageResponse

    //check email
    @POST("api/auth/check-email")
    suspend fun checkEmailExists(@Body email: String): Response<MessageResponse>

    //google login
    @POST("api/auth/google")
    suspend fun googleLogin(@Body idToken: GoogleLoginRequest): AuthResponse

    //facebook login
    @POST("api/auth/facebook")
    suspend fun facebookLogin(@Body accessToken: FbLoginRequest): AuthResponse
}
