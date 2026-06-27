package com.example.fruitapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fruitapp.ui.auth.BeginScreen
import com.example.fruitapp.ui.auth.LoginScreen
import com.example.fruitapp.ui.auth.RegisterScreen
import com.example.fruitapp.ui.auth.view.ForgotPasswordScreen
import com.example.fruitapp.ui.auth.view.OtpVerificationScreen
import com.example.fruitapp.ui.auth.view.ResetPasswordScreen
import com.example.fruitapp.ui.auth.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    val viewModel: AuthViewModel = hiltViewModel()
    
    NavHost(
        navController = navController,
        startDestination = Route.BEGIN
    ) {
        composable(Route.BEGIN) {
            BeginScreen(navController)
        }
        
        composable(Route.REGISTER) {
            RegisterScreen(viewModel = viewModel, navController = navController)
        }
        
        composable(Route.LOGIN) {
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        
        composable(Route.FORGOT_PASSWORD) {
            ForgotPasswordScreen(viewModel = viewModel, navController = navController)
        }
        
        // Nhận email từ trang ForgotPassword
        composable(Route.VERIFY_OTP + "/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpVerificationScreen(
                email = email,
                viewModel = viewModel,
                navController = navController
            )
        }
        
        // Nhận email và otp từ trang VerifyOtp
        composable(Route.RESET_PASSWORD + "/{email}/{otp}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val otp = backStackEntry.arguments?.getString("otp") ?: ""
            
            ResetPasswordScreen(
                email = email,
                otp = otp,
                viewModel = viewModel,
                navController = navController
            )
        }
        
        composable(Route.HOME) {
            // Màn hình trang chủ của bạn
        }
    }
}
