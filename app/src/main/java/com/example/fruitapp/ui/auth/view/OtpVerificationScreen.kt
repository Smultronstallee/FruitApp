package com.example.fruitapp.ui.auth.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Dialpad
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun OtpVerificationScreen(
    email: String, // Thêm tham số email ở đây
    viewModel: AuthViewModel,
    navController: NavController
) {
    var otp by remember { mutableStateOf("") }
    var attempted by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.isLoading, viewModel.errorMessage) {
        if (attempted && !viewModel.isLoading) {
            if (viewModel.errorMessage == null) {
                snackbarHostState.showSnackbar(
                    message = "Xác thực OTP thành công!"
                )
                navController.navigate(Route.RESET_PASSWORD + "/$email/$otp")
            } else {
                viewModel.errorMessage?.let { snackbarHostState.showSnackbar(it) }
            }
            attempted = false // Reset lại trạng thái sau mỗi lần thử
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
                            text = "Xác thực OTP",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Text(
                        text = "Mã OTP đã được gửi đến $email\nVui lòng kiểm tra email của bạn.",
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = otp,
                        onValueChange = { otp = it },
                        placeholder = { Text("Nhập mã OTP...", color = Color.Gray) },
                        leadingIcon = { Icon(Icons.Default.Dialpad, contentDescription = null) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = colorResource(id = R.color.blue),
                            unfocusedBorderColor = Color.Transparent,
                            focusedLeadingIconColor = colorResource(id = R.color.blue),
                            unfocusedLeadingIconColor = Color.Gray
                        )
                    )

                    Button(
                        onClick = {
                            if (otp.isNotEmpty()) {
                                attempted = true
                                viewModel.clearErrorMessage()
                                viewModel.verifyOtp(email, otp)
                            }
                        },
                        enabled = !viewModel.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.blue)
                        )
                    ) {
                        if (viewModel.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = Color.White
                            )
                        } else {
                            Text(text = "Xác nhận OTP", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
