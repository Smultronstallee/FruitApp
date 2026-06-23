package com.example.fruitapp.ui.cart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun CartCard(
    name: String = "Lựu đỏ Ai Cập Ngon lắm á",
    category: String = "Trái cây",
    price: String = "55.000đ",
    unit: String = "1kg",
    imageRes: Int = R.drawable.luu,
    onDelete: () -> Unit = {}
) {
    var isChecked by remember { mutableStateOf(false) }
    var quantity by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(
                color = colorResource(id = R.color.gray_dark),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 1. tick choose - Ô vuông bo tròn 5dp có fill màu blue
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(
                    if (isChecked) colorResource(id = R.color.blue) else Color.Transparent
                )
                .border(
                    width = 2.dp,
                    color = if (isChecked) colorResource(id = R.color.blue) else Color.White,
                    shape = RoundedCornerShape(5.dp)
                )
                .clickable { isChecked = !isChecked },
            contentAlignment = Alignment.Center
        ) {
            if (isChecked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // 2. image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // 3. CỘT THÔNG TIN: Tên danh mục, tên sản phẩm, giá
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = category,
                color = colorResource(id = R.color.blue),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = name,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                // Bỏ maxLines để hiển thị hết nội dung dài
            )
            
            // GIÁ VÀ UNIT TRÊN CÙNG 1 HÀNG
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = price,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "/$unit",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }

        // 4. CỘT SỐ LƯỢNG: cái - số lượng +
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(14.dp))
                    .padding(horizontal = 5.dp)
            ) {
                IconButton(
                    onClick = { if (quantity > 1) quantity-- },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(Icons.Default.Remove, null, tint = Color.Black, modifier = Modifier.size(16.dp))
                }
                Text(
                    text = quantity.toString(),
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = { quantity++ },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(Icons.Default.Add, null, tint = Color.Black, modifier = Modifier.size(16.dp))
                }
            }
        }

        // 5. Nút xóa (thùng rác) nằm ở góc trên bên phải
        Box(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(start = 4.dp)
        ) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Xóa",
                    tint = Color.Red,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}
