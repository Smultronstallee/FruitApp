package com.example.fruitapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fruitapp.ui.auth.BeginScreen
import com.example.fruitapp.ui.auth.ForgotPasswordScreen
import com.example.fruitapp.ui.auth.LoginScreen
import com.example.fruitapp.ui.auth.RegisterScreen
import com.example.fruitapp.ui.auth.viewmodel.AuthViewModel
import com.example.fruitapp.ui.screen.user.HomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController){
    val viewModel : AuthViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Route.BEGIN
    ){
        composable(Route.BEGIN){
            BeginScreen(navController)
        }
        composable(Route.REGISTER){
            RegisterScreen(viewModel= viewModel, navController)
        }
        composable(Route.LOGIN){
            LoginScreen(viewModel = viewModel, navController)
        }
        composable(Route.FORGOT_PASSWORD) {
            ForgotPasswordScreen(viewModel = viewModel, navController)
        }

    }
}
