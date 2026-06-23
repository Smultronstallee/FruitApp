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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun RegisterScreen(
    viewModel: AuthViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf<String?>(null) }

    // Theo dõi đăng ký thành công
    LaunchedEffect(viewModel.isLoading, viewModel.errorMessage) {
        if (!viewModel.isLoading && viewModel.errorMessage == null &&
            (userName.isNotEmpty() || email.isNotEmpty())) {
            // reset sau khi thành công
        }
    }

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
                    text = "Đăng ký",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Bắt đầu hành trình mới cùng FruitApp 🍃",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Ô nhập Họ tên
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    placeholder = { Text("Họ và tên", color = Color.Gray) }, // ← placeholder thay label
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
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

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
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

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text("Mật khẩu", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
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

                // Hiển thị lỗi
                viewModel.errorMessage?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 13.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Button(
                    onClick = {
                        viewModel.register(userName, email, password)
                        successMessage = "Đăng ký thành công! Vui lòng đăng nhập."
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
                        Text(text = "Đăng ký", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // Thông báo thành công
                successMessage?.let {
                    if (viewModel.errorMessage == null && !viewModel.isLoading) {
                        Text(
                            text = it,
                            color = Color.Green,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        LaunchedEffect(Unit) {
                            kotlinx.coroutines.delay(1500)
                            navController.navigate(Route.LOGIN) {
                                popUpTo(Route.REGISTER) { inclusive = true }
                            }
                        }
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
                    Text(text = "Bạn đã có tài khoản? ", color = Color.White, fontSize = 14.sp)
                    Text(
                        text = "Đăng nhập",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(Route.LOGIN)
                        }
                    )
                }
            }
        }
    }
}