package com.example.fruitapp.ui.auth.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.ui.auth.viewmodel.AuthViewModel
import com.example.fruitapp.ui.navigation.Route

@Composable
fun ResetPasswordScreen(
    email: String, // Nhận email từ Navigation
    otp: String,   // Nhận otp từ Navigation
    viewModel: AuthViewModel,
    navController: NavController
) {
    var passwordNew by remember { mutableStateOf("") }
    var confirmPasswordNew by remember { mutableStateOf("") }
    var attempted by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var passwordTouched by remember { mutableStateOf(false) }
    var confirmPasswordTouched by remember { mutableStateOf(false) }

    // Quy tắc kiểm tra mật khẩu
    val passwordError = remember(passwordNew) {
        when {
            passwordNew.isEmpty() -> null // Không báo lỗi khi chưa nhập
            passwordNew.length < 6 -> "Mật khẩu tối thiểu 6 ký tự"
            !passwordNew.any { it.isUpperCase() } -> "Cần ít nhất 1 chữ hoa"
            !passwordNew.any { it.isLowerCase() } -> "Cần ít nhất 1 chữ thường"
            !passwordNew.any { !it.isLetterOrDigit() } -> "Cần ít nhất 1 ký tự đặc biệt"
            else -> null
        }
    }

    val confirmPasswordError = remember(passwordNew, confirmPasswordNew) {
        when {
            confirmPasswordNew.isNotEmpty() && passwordNew != confirmPasswordNew -> "Mật khẩu xác nhận không khớp"
            else -> null
        }
    }

    LaunchedEffect(viewModel.isLoading, viewModel.errorMessage) {
        if (attempted && !viewModel.isLoading) {
            if (viewModel.errorMessage == null) {
                snackbarHostState.showSnackbar(message = "Đặt mật khẩu thành công!")
                navController.navigate(Route.LOGIN) {
                    popUpTo(Route.LOGIN) { inclusive = true }
                }
            } else {
                viewModel.errorMessage?.let { snackbarHostState.showSnackbar(it) }
            }
            attempted = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Black.copy(alpha = 0.5f))
                        .border(
                            width = 2.dp,
                            color = colorResource(id = R.color.blue),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Header
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "Tạo mật khẩu mới",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "Vui lòng nhập mật khẩu mới cho tài khoản của bạn.",
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = passwordNew,
                        onValueChange = {
                            passwordNew = it
                            localError = null // Xóa lỗi khi gõ lại
                        },
                        placeholder = { Text("Mật khẩu mới", color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = if (passwordVisible) "Hide password" else "Show password")
                            }
                        },
                        isError = passwordTouched && passwordError != null,
                        modifier = Modifier.fillMaxWidth().onFocusChanged {
                            if (!it.isFocused) {
                                passwordTouched = true
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(id = R.color.blue),
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Red
                        )
                    )

                    OutlinedTextField(
                        value = confirmPasswordNew,
                        onValueChange = {
                            confirmPasswordNew = it
                            localError = null
                        },
                        placeholder = { Text("Xác nhận mật khẩu", color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                                Icon(imageVector = image, contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password")
                            }
                        },
                        isError = confirmPasswordTouched && confirmPasswordError != null,
                        modifier = Modifier.fillMaxWidth().onFocusChanged {
                            if (!it.isFocused) {
                                confirmPasswordTouched = true
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(id = R.color.blue),
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Red
                        )
                    )

                    // Hiển thị lỗi
                    val displayPasswordError = if (passwordTouched) passwordError else null
                    val displayConfirmPasswordError = if (confirmPasswordTouched) confirmPasswordError else null
                    val displayError = localError ?: displayPasswordError ?: displayConfirmPasswordError ?: viewModel.errorMessage
                    displayError?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            fontSize = 13.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {
                            when {
                                passwordNew.isBlank() || confirmPasswordNew.isBlank() -> {
                                    localError = "Vui lòng nhập đầy đủ thông tin"
                                }
                                passwordError != null -> {
                                    localError = passwordError
                                }
                                confirmPasswordError != null -> {
                                    localError = confirmPasswordError
                                }
                                else -> {
                                    attempted = true
                                    viewModel.clearErrorMessage()
                                    viewModel.resetPassword(email, otp, passwordNew)
                                }
                            }
                        },
                        enabled = !viewModel.isLoading && passwordError == null && confirmPasswordError == null && passwordNew.isNotEmpty() && confirmPasswordNew.isNotEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.blue),
                            disabledContainerColor = Color.Gray
                        )
                    ) {
                        if (viewModel.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = Color.White
                            )
                        } else {
                            Text(text = "Xác nhận đổi mật khẩu", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}