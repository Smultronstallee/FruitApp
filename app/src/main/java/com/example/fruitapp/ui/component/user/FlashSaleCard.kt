package com.example.fruitapp.ui.component.user

// Thêm các import cho Animation
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Cần thiết để dùng 'by'
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer // Để dùng hiệu ứng động
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    name: String = "Sản phẩm",
    price: String = "0đ",
    unit: String = "1kg",
    discount: Int = 0,
    soldPercent: Float = 0f,
    imageRes: Int = R.drawable.begin_img
) {
    // TẠO ANIMATION CHO ICON
    val infiniteTransition = rememberInfiniteTransition(label = "fire_pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "fire_scale"
    )

    Column(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        // 1. Hình ảnh
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            if (discount > 0) {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(bottomEnd = 10.dp)
                ) {
                    Text(
                        text = "-$discount%",
                        color = Color.Red,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
        }

        // 2. Nội dung
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = name,
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
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = price, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = "/$unit", color = Color.Gray, fontSize = 11.sp, modifier = Modifier.padding(start = 2.dp))
                }
                Text(text = "Đã bán", fontSize = 10.sp, color = Color.Gray)
            }

            // Progress Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(soldPercent.coerceIn(0f, 1f))
                        .fillMaxHeight()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color(0xFFEA2727), Color(0xFFEBDF32))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                )

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // ICON ĐÃ ĐƯỢC THÊM ANIMATION
                    Icon(
                        imageVector = Icons.Default.Whatshot,
                        contentDescription = null,
                        tint = Color.Yellow,
                        modifier = Modifier
                            .size(12.dp)
                            .graphicsLayer(
                                scaleX = scale, // Áp dụng tỷ lệ scale X
                                scaleY = scale  // Áp dụng tỷ lệ scale Y
                            )
                    )
                    Text(
                        text = " ${(soldPercent * 100).toInt()}%",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Nút Mua ngay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(colorResource(id = R.color.blue), Color(0xFF143F4D))
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