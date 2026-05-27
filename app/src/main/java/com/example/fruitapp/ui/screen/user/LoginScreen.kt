package com.example.fruitapp.ui.screen.user

import android.R.attr.text
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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
import com.example.fruitapp.R

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Transparent,
                        focusedLabelColor = colorResource(id = R.color.blue),
                        unfocusedLabelColor = Color.Gray,
                        focusedLeadingIconColor = colorResource(id = R.color.blue),
                        unfocusedLeadingIconColor = Color.Gray
                    )
                )

                // Ô nhập Mật khẩu
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mật khẩu") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Transparent,
                        focusedLabelColor = colorResource(id = R.color.blue),
                        unfocusedLabelColor = Color.Gray,
                        focusedLeadingIconColor = colorResource(id = R.color.blue),
                        unfocusedLeadingIconColor = Color.Gray
                    ))

                     //quên mật khẩu
                    Text("Quên mật khẩu?",
                        modifier = Modifier
                            .align(Alignment.End).clickable{/**/},
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin
                    )

                Button(
                    onClick = { /* Xử lý đăng nhập */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.blue)
                    )
                ) {
                    Text(text = "Đăng nhập", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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

                // Nút Facebook và Google (Không viền, Không fill, Hình tròn)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { /* Google */ },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_gg),
                            contentDescription = "Google",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                    IconButton(
                        onClick = { /* Facebook */ },
                        modifier = Modifier.size(64.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_fb),
                            contentDescription = "Facebook",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )
                    }
                }

                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Bạn chưa có tài khoản?", color = Color.White, fontSize = 14.sp)
                    Text(
                        text = "Đăng ký ngay ",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { /* Chuyển hướng sang Login */ }
                    )
                }
            }
        }
    }
}