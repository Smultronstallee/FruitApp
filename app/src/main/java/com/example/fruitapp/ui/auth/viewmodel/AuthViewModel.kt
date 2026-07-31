package com.example.fruitapp.ui.auth.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.data.model.dto.request.ForgotPasswordRequest
import com.example.fruitapp.data.model.dto.request.LoginRequest
import com.example.fruitapp.data.model.dto.request.RegisterRequest
import com.example.fruitapp.data.model.dto.request.ResetPasswordRequest
import com.example.fruitapp.data.model.dto.request.VerifyOtpRequest
import com.example.fruitapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var emailError by mutableStateOf<String?>(null)
        private set

    var authError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    // Thêm các state để nhận biết trạng thái thành công
    var isRegisterSuccess by mutableStateOf(false)
        private set

    var isLoginSuccess by mutableStateOf(false)
        private set

    fun clearErrorMessage() {
        authError = null
        emailError = null
    }

    fun resetState() {
        isRegisterSuccess = false
        isLoginSuccess = false
        authError = null
        emailError = null
    }

    //register
    fun register(userName: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                authError = null
                isRegisterSuccess = false

                repository.register(RegisterRequest(userName, email, password))

                isRegisterSuccess = true

            } catch (e: Exception) {
                authError = e.message ?: "Đăng ký thất bại"
            } finally {
                isLoading = false
            }
        }
    }

    //login
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                authError = null
                isLoginSuccess = false
                repository.login(LoginRequest(email, password))
                isLoginSuccess = true
            } catch (e: Exception) {
                authError = e.message ?: "Đăng nhập thất bại"
            } finally {
                isLoading = false
            }
        }
    }

    //google login
    fun googleLogin(idToken: String) = viewModelScope.launch {
        try {
            isLoading = true
            authError = null
            isLoginSuccess = false
            repository.googleLogin(idToken)
            isLoginSuccess = true
        } catch (e: Exception) {
            e.printStackTrace()
            authError = e.message ?: "Lỗi đăng nhập Google"
        } finally {
            isLoading = false
        }
    }

    //facebook login
    fun facebookLogin(accessToken: String) = viewModelScope.launch {
        try {
            isLoading = true
            authError = null
            isLoginSuccess = false
            repository.facebookLogin(accessToken)
            isLoginSuccess = true
        } catch (e: Exception) {
            Log.e("FACEBOOK_LOGIN", "LOGIN FAILED", e)
            authError = e.message ?: "Lỗi đăng nhập Facebook"
        } finally {
            isLoading = false
        }
    }

    // Các hàm khác giữ nguyên logic nhưng có cập nhật isLoading
    fun forgotPassword(email: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                emailError = null
                repository.forgotPassword(ForgotPasswordRequest(email))
            } catch (e: Exception) {
                emailError = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun verifyOtp(email: String, otp: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                authError = null
                repository.verifyOtp(VerifyOtpRequest(email, otp))
            } catch (e: Exception) {
                authError = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun resetPassword(email: String, otp: String, passwordNew: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                authError = null
                repository.resetPassword(ResetPasswordRequest(email, otp, passwordNew))
            } catch (e: Exception) {
                authError = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun checkEmailExists(email: String) {
        viewModelScope.launch {
            try {
                val exists = repository.checkEmailExists(email)
                if (exists) {
                    emailError = "Email này đã được sử dụng"
                } else if (emailError == "Email này đã được sử dụng") {
                    emailError = null
                }
            } catch (e: Exception) { }
        }
    }
}
