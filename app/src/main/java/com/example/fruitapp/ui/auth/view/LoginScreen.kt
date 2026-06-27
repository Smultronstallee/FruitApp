package com.example.fruitapp.ui.auth

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.ui.auth.viewmodel.AuthViewModel
import com.example.fruitapp.ui.navigation.Route

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var attempted by remember { mutableStateOf(false) }
    var localError by remember { mutableStateOf<String?>(null) }
    var emailTouched by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Quy tắc kiểm tra
    val isEmailValid = remember(email) {
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Đối với đăng nhập, chỉ cần kiểm tra mật khẩu không trống.
    val isPasswordEntered = remember(password) {
        password.isNotEmpty()
    }

    LaunchedEffect(viewModel.isLoading, viewModel.errorMessage) {
        if (attempted && !viewModel.isLoading && viewModel.errorMessage == null) {
            snackbarHostState.showSnackbar(message = "Đăng nhập thành công!")
            attempted = false
            navController.navigate(Route.HOME) {
                popUpTo(Route.LOGIN) { inclusive = true }
            }
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
                    Text(
                        text = "Đăng nhập",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Chào mừng quay trở lại với FruitApp 🍃",
                        fontSize = 14.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Ô nhập Email
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                localError = null
                                viewModel.clearErrorMessage()
                            },
                            placeholder = { Text("Email", color = Color.Gray) },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                            isError = emailTouched && !isEmailValid,
                            modifier = Modifier.fillMaxWidth().onFocusChanged {
                                if (!it.isFocused) {
                                    emailTouched = true
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
                                focusedLeadingIconColor = colorResource(id = R.color.blue),
                                unfocusedLeadingIconColor = Color.Gray,
                                errorBorderColor = Color.Red
                            )
                        )
                        if (emailTouched && !isEmailValid) {
                            Text("Email không hợp lệ", color = Color.Red, fontSize = 11.sp, modifier = Modifier.padding(start = 8.dp))
                        }
                    }

                    // Ô nhập Mật khẩu
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                localError = null
                                viewModel.clearErrorMessage()
                            },
                            placeholder = { Text("Mật khẩu", color = Color.Gray) },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                            visualTransformation = PasswordVisualTransformation(),
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
                                unfocusedLeadingIconColor = Color.Gray,
                                errorBorderColor = Color.Red
                            )
                        )
                    }

                    Text(
                        "Quên mật khẩu?",
                        modifier = Modifier
                            .align(Alignment.End)
                            .clickable { navController.navigate(Route.FORGOT_PASSWORD) },
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    // Hiển thị lỗi từ server
                    val displayError = localError ?: viewModel.errorMessage
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
                                email.isBlank() || password.isBlank() -> {
                                    localError = "Vui lòng nhập đầy đủ thông tin"
                                }
                                !isEmailValid -> {
                                    localError = "Email không đúng định dạng"
                                }
                                else -> {
                                    attempted = true
                                    viewModel.login(email, password)
                                }
                            }
                        },
                        enabled = !viewModel.isLoading && !(email.isNotEmpty() && !isEmailValid) && viewModel.errorMessage == null,
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
                            Text(text = "Đăng nhập", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Row(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
                        Text(
                            text = "Hoặc",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Gray)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.4:8080/oauth2/authorization/google"))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_gg),
                                contentDescription = "Google",
                                modifier = Modifier.size(48.dp).clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.width(24.dp))
                        IconButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.4:8080/oauth2/authorization/facebook"))
                                context.startActivity(intent)
                            },
                            modifier = Modifier.size(64.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_fb),
                                contentDescription = "Facebook",
                                modifier = Modifier.size(48.dp).clip(CircleShape)
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Bạn chưa có tài khoản? ", color = Color.White, fontSize = 14.sp)
                        Text(
                            text = "Đăng ký ngay",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { navController.navigate(Route.REGISTER) }
                        )
                    }
                }
            }
        }
    }
}