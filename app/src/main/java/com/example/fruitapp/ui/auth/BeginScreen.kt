package com.example.fruitapp.ui.screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun BeginScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Lớp 1: Hình nền
        Image(
            painter = painterResource(id = R.drawable.begin_img),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Lớp 2: Overlay màu đen mờ 20%
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        )

        // Lớp 3: Nội dung
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp), // Padding này tạo khoảng cách với mép màn hình
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Trái cây có thể giúp tăng lượng chất xơ và kali là những chất dinh dưỡng quan trọng mà nhiều người dùng không có đủ trong chế độ ăn uống của họ.",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                lineHeight = 28.sp
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 40.dp) // Khoảng cách giữa chữ và nút
                    .fillMaxWidth(0.85f)
                    .height(56.dp), // Chiều cao nút 56dp (dễ bấm)
                shape = RoundedCornerShape(14.dp), // Border radius 16dp
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Bắt đầu với FruitApp", 
                    fontSize = 18.sp
                )
            }
        }
    }
}