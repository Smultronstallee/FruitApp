package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

// Định nghĩa model để nhận dữ liệu từ BE
data class ProductFlashSale(
    val id: Int,
    val name: String,
    val price: String,
    val discount: Int,
    val soldPercent: Float, // Ví dụ: 0.6f cho 60%
    val imageRes: Int
)

@Composable
fun FlashSale() {
    // Giả lập danh sách dữ liệu
    val productList = listOf(
        ProductFlashSale(1, "Lựu đỏ Ai Cập", "155.000đ", 20, 0.8f, R.drawable.luu),
        ProductFlashSale(2, "Combo Trái cây", "250.000đ", 15, 0.4f, R.drawable.begin_img),
        ProductFlashSale(3, "Banner Ưu đãi", "120.000đ", 10, 0.6f, R.drawable.banner),
        ProductFlashSale(4, "Táo Envy", "80.000đ", 30, 0.95f, R.drawable.luu)
    )

    // 1. Lề ngoài 16.dp đồng bộ với Banner và các phần khác
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.gray_dark),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp) // 2. Đệm trong 20.dp giống Banner
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.FlashOn,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Đang giảm mạnh",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Còn 00 : 00 : 00",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .background(Color.Red, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productList) { product ->
                    // 3. Sử dụng ProductCard với các tham số chính xác
                    ProductCard(
                        modifier = Modifier.width(150.dp),
                        name = product.name,
                        price = product.price,
                        discount = product.discount,
                        soldPercent = product.soldPercent,
                        imageRes = product.imageRes
                    )
                }
            }
        }
    }
}