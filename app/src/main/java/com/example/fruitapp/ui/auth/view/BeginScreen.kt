package com.example.fruitapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fruitapp.R
import com.example.fruitapp.ui.navigation.Route

@Composable
fun BeginScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Lớp 1: Hình nền
        Image(
            painter = painterResource(id = R.drawable.begin_img),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.BottomCenter)
                .background(Color.Black)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .align(Alignment.BottomCenter)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Trái cây có thể giúp tăng lượng chất xơ và kali là những chất dinh dưỡng quan trọng mà nhiều người dùng không có đủ trong chế độ ăn uống của họ.",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 28.sp
            )

            Button(
                onClick = { navController.navigate(Route.LOGIN) },
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(0.85f)
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Bắt đầu với FruitApp", fontSize = 18.sp)
            }
        }
    }
}