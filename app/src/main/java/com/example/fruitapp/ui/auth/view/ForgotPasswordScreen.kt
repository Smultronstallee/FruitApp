package com.example.fruitapp.ui.auth.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.ui.auth.viewmodel.AuthViewModel
import com.example.fruitapp.ui.navigation.Route

@Composable
fun ForgotPasswordScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var attempted by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }
    var emailTouched by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    val isEmailValid = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    LaunchedEffect(viewModel.isLoading, viewModel.authError) {
        if (attempted && !viewModel.isLoading) {
            if (viewModel.authError == null) {
                snackbarHostState.showSnackbar(
                    message = "OTP đã được gửi đến email của bạn!" 
                )
                navController.navigate(Route.VERIFY_OTP + "/$email")
            }
            attempted = false
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
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
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp),
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
                    // Tiêu đề kèm nút Quay lại ở trên cùng
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
                            text = "Quên mật khẩu",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "Nhập email để lấy lại mật khẩu cùng FruitApp 🍃",
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            localError = null
                            viewModel.clearErrorMessage()
                        },
                        placeholder = { Text("Email", color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth().onFocusChanged {
                            if (!it.isFocused) {
                                emailTouched = true
                            }
                        },
                        isError = emailTouched && !isEmailValid,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(id = R.color.blue),
                            unfocusedBorderColor = Color.Transparent,
                            errorBorderColor = Color.Red,
                            focusedLeadingIconColor = colorResource(id = R.color.blue),
                            unfocusedLeadingIconColor = Color.Gray
                        )
                    )
                    if (emailTouched && !isEmailValid) {
                        Text(
                            text = "Email không đúng định dạng",
                            color = Color.Red,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start = 8.dp, top = 2.dp))
                    }

                    val displayError = localError ?: viewModel.emailError
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
                                email.isBlank() -> {
                                    localError = "Vui lòng nhập email"
                                }
                                !isEmailValid -> {
                                    localError = "Email không đúng định dạng"
                                }
                                else -> {
                                    attempted = true
                                    viewModel.clearErrorMessage()
                                    viewModel.forgotPassword(email)
                                }
                            }
                        },
                        enabled = !viewModel.isLoading && !(email.isNotEmpty() && !isEmailValid) && viewModel.emailError == null,
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
                            Text(text = "Gửi email", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}