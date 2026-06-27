package com.example.fruitapp.ui.auth.viewmodel

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
class   AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun clearErrorMessage(){
        errorMessage=null
    }

    var isLoading by mutableStateOf(false)
        private set

    //register
    fun register(userName: String, email: String, password: String){
        viewModelScope.launch {
            try{
                isLoading = true
                repository.register(RegisterRequest(userName, email, password))
            } catch (e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        
        }
    }

    //login
    fun login(email: String, password: String){
        viewModelScope.launch {
            try{
                isLoading = true
                repository.login(LoginRequest(email, password))
        }catch(e: Exception){
            errorMessage = e.message
        } finally {
            isLoading = false
        }
        }
    }

    //forgot password
    fun forgotPassword(email: String){
        viewModelScope.launch {
            try{
                isLoading = true
                repository.forgotPassword(ForgotPasswordRequest(email))
            }catch (e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    //verify otp
    fun verifyOtp(email: String, otp: String){
        viewModelScope.launch {
            try{
                isLoading = true
                repository.verifyOtp(
                    VerifyOtpRequest(
                        email = email,
                        otp = otp
                    )
                )
            }catch(e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    //reset password
    fun resetPassword(email: String, otp: String, passwordNew: String){
        viewModelScope.launch {
            try{
                isLoading = true
                repository.resetPassword(
                    ResetPasswordRequest(
                        email = email,
                        otp = otp,
                        passwordNew = passwordNew
                    )
                )
            } catch(e: Exception){
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    //check email exists
    fun checkEmailExists(email: String) {
        viewModelScope.launch {
            try {
                // Không bật isLoading để kiểm tra ngầm
                val exists = repository.checkEmailExists(email)
                if (exists) {
                    errorMessage = "Email này đã được sử dụng"
                } else {
                    // Chỉ xóa lỗi khi lỗi đó là về email tồn tại
                    if (errorMessage == "Email này đã được sử dụng") {
                        errorMessage = null
                    }
                }
            } catch (e: Exception) {
                // Bỏ qua lỗi mạng trong trường hợp kiểm tra ngầm để không làm phiền người dùng
            }
        }
    }

}
