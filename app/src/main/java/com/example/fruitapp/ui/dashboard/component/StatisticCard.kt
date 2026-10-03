package com.example.fruitapp.ui.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatisticCard() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            StatCardItem(
                title = "Tổng người dùng",
                value = "1,250",
                icon = Icons.Default.Person,
                iconColor = Color(0xFF004AAD), // Xanh dương đậm
                iconBgColor = Color(0xFFC3D8FF)
            )
        }
        item {
            StatCardItem(
                title = "Tổng sản phẩm",
                value = "450",
                icon = Icons.Default.Inventory,
                iconColor = Color(0xFF2E7D32), // Xanh lá đậm (pastel-ish)
                iconBgColor = Color(0xFFC3FFCE)
            )
        }
        item {
            StatCardItem(
                title = "Tổng đơn hàng",
                value = "3,200",
                icon = Icons.AutoMirrored.Filled.ReceiptLong,
                iconColor = Color(0xFFFFC107), // Vàng
                iconBgColor = Color(0xFFFFF6C3)
            )
        }
        item {
            StatCardItem(
                title = "Doanh thu",
                value = "45.5M",
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                iconColor = Color(0xFF7B1FA2), // Tím
                iconBgColor = Color(0xFFDDBEFA)
            )
        }
    }
}

@Composable
private fun StatCardItem(
    title: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    iconBgColor: Color
) {
    Card(
        modifier = Modifier
            .width(185.dp)
            .height(115.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Icon tròn bao quanh
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(color = iconBgColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(22.dp)
                    )
                }
                
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }

            Text(
                text = value,
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
