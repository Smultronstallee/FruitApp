package com.example.fruitapp.ui.auth.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.data.model.dto.ForgotPasswordRequest
import com.example.fruitapp.data.model.dto.LoginRequest
import com.example.fruitapp.data.model.dto.RegisterRequest
import com.example.fruitapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
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
}
