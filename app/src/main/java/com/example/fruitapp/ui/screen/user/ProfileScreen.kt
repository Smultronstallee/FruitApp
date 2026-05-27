package com.example.fruitapp.ui.screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import com.example.fruitapp.R

@Composable
fun ProfileScreen() {
    // Khai báo các biến State để giữ dữ liệu chỉnh sửa
    var name by remember { mutableStateOf("Nguyễn Văn A") }
    var email by remember { mutableStateOf("nguyenvana@gmail.com") }
    var phone by remember { mutableStateOf("0987654321") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState()) // Cho phép cuộn
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        // 1. Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Quay lại",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { /* Quay lại */ }
            )
            Text(
                text = "Hồ sơ cá nhân",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            )
        }

        // 2. Nội dung chính
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Phần Avatar - Căn trái
            Box(modifier = Modifier
                .size(130.dp)
                .align(Alignment.Start), // Căn trái cụm này
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.avatar), // Dùng logo_fb làm placeholder
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape),
                    contentScale = ContentScale.Crop
                )
                // Icon đổi ảnh
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { /* Chọn ảnh */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt, 
                        contentDescription = null, 
                        tint = Color.Black, 
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Chiều rộng cố định cho nhãn để các ô nhập liệu thẳng hàng
            val labelWidth = 100.dp

            // Cấu hình màu sắc dùng chung cho các TextFields
            val textFieldColors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = colorResource(id = R.color.blue),
                unfocusedBorderColor = Color.Transparent,
                cursorColor = colorResource(id = R.color.blue)
            )

            // Ô Họ tên
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Họ tên",
                    color = Color.White,
                    modifier = Modifier.width(labelWidth),
                    fontSize = 16.sp,
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = textFieldColors,
                    singleLine = true
                )
            }

            // Ô Email
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Email",
                    color = Color.White,
                    modifier = Modifier.width(labelWidth),
                    fontSize = 16.sp,
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = textFieldColors,
                    singleLine = true
                )
            }

            // Ô Số điện thoại
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Số điện thoại",
                    color = Color.White,
                    modifier = Modifier.width(labelWidth),
                    fontSize = 16.sp,
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = textFieldColors,
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Nút Lưu
            Button(
                onClick = { /* Xử lý lưu */ },
                modifier = Modifier
                    .width(138.dp)
                    .height(44.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue)
                )
            ) {
                Text("Cập nhật", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Đổi mật khẩu", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
            }
        }
    }
}