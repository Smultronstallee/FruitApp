package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun CardProduct(
    modifier: Modifier = Modifier, // Thêm modifier để linh hoạt kích thước
    name: String = "Táo Envy Mỹ",
    price: String = "55.000đ",
    unit: String = "1kg",
    category: String = "Trái cây",
    rating: Double = 4.9,
    imageRes: Int = R.drawable.luu,
    isSuggested: Boolean = false,
    tag: String = "Dành cho bạn",
    reviewCount: Int = 120,
    soldCount: Int = 200,
    showAddButton: Boolean = false
) {
    var isFavorite by remember { mutableStateOf(false) }
    val contentColor = Color.Black
    val secondaryColor = Color.Gray

    Column(
        modifier = modifier // Sử dụng modifier từ bên ngoài truyền vào
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Nhãn danh mục bên trái: bo tròn phía bên phải
                Surface(
                    color = colorResource(id = R.color.gray_dark).copy(alpha = 0.8f),
                    shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                ) {
                    Text(
                        text = category,
                        color = Color.White,
                        fontSize = 9.sp,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                // Yêu thích bên phải: bo góc dưới-trái
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            Color.White.copy(alpha = 0.5f), 
                            shape = RoundedCornerShape(bottomStart = 15.dp)
                        )
                        .clickable { isFavorite = !isFavorite },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavorite) Color.Red else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // 2. Nội dung
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Tên sản phẩm
            Text(
                text = name,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )

            // Logic hiển thị: Gợi ý vs Bán chạy
            if (isSuggested) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nhãn Gradient cho "Dành cho bạn"
                    Text(
                        text = tag,
                        fontSize = 11.sp,
                        style = TextStyle(
                            brush = Brush.horizontalGradient(
                                colors = listOf(colorResource(id = R.color.blue), Color(0xFF143F4D))
                            )
                        ),
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(12.dp))
                        Text(
                            text = " $rating", 
                            fontSize = 11.sp, 
                            color = secondaryColor 
                        )
                    }
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, tint = Color(0xFFFFC107), modifier = Modifier.size(14.dp))
                    Text(
                        text = " $rating", 
                        fontSize = 12.sp, 
                        color = contentColor, 
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " | Đã bán $soldCount", 
                        fontSize = 12.sp, 
                        color = secondaryColor 
                    )
                }
            }

            // Giá và Hành động
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = price,
                        color = contentColor, 
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "/$unit", 
                        color = secondaryColor,
                        fontSize = 12.sp
                    )
                }
                
                if (!showAddButton) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .background(colorResource(id = R.color.blue), CircleShape)
                            .clickable { /* Thêm */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, null, tint = Color.White, modifier = Modifier.size(18.dp))
                    }
                }
            }

            if (showAddButton) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(colorResource(id = R.color.blue), Color(0xFF143F4D))
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable { /* Thêm vào giỏ hàng */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Thêm vào giỏ hàng", 
                        color = Color.White, 
                        fontSize = 12.sp, 
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
