package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fruitapp.R

@Composable
fun Footer() {
    // Trạng thái lưu mục đang được chọn
    var selectedIndex by remember { mutableIntStateOf(0) }

    // Danh sách các mục menu theo yêu cầu
    val items = listOf(
        NavigationItem(Icons.Default.Home),
        NavigationItem(Icons.Default.Category),
        NavigationItem(Icons.Default.Favorite),
        NavigationItem(Icons.Default.Notifications),
        NavigationItem(Icons.Default.Person)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.black)) // Nền đen cho footer
            .padding(vertical = 8.dp, horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            val isActive = selectedIndex == index

            Box(
                modifier = Modifier
                    .height(45.dp)
                    .weight(1f) // Chia đều không gian cho các icon
                    .clip(RoundedCornerShape(12.dp))
                    // Nếu đang active thì có nền màu xanh blue
                    .background(if (isActive) colorResource(id = R.color.blue) else Color.Transparent)
                    .clickable { selectedIndex = index },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    // Icon màu trắng nếu active, màu xám nếu không
                    tint = if (isActive) Color.White else Color.Gray,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

// Lớp dữ liệu cho các mục điều hướng
data class NavigationItem(
    val icon: ImageVector
)
