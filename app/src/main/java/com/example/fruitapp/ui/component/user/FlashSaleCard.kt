package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun ProductCard() {
    Column(
        modifier = Modifier
            .width(180.dp)
            .height(300.dp)
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        // 1. Hình ảnh chiếm 2/3
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.begin_img), // Placeholder
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Badge phần trăm giảm giá đè lên hình
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Red, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                    .align(Alignment.TopStart)
            ) {
                Text(text = "-20%", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        // 2. Nội dung chiếm 1/3
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f)
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tên sản phẩm Fruit",
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "25.000đ", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(text = "Đã bán", fontSize = 10.sp, color = Color.Gray)
            }

            // Thanh Progress Đã bán 60% có Gradient và Icon Lửa
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
            ) {
                // Gradient theo phần trăm (Ví dụ 60%)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFE91E63), Color(0xFFFF5722))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                )
                
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Whatshot,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(text = "Đã bán 60%", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Button Mua ngay Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF2F93B3), Color(0xFF143F4D))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { /* Xử lý mua */ },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Mua ngay", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
