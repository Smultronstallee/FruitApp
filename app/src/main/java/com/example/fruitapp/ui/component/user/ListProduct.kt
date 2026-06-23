package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fruitapp.R
import com.example.fruitapp.ui.cart.component.CardProduct

// Đổi tên thành ShopProduct để tránh trùng lặp với file khác
data class ShopProduct(
    val name: String,
    val price: String,
    val unit: String,
    val category: String,
    val rating: Double,
    val soldCount: Int,
    val imageRes: Int
)

@Composable
fun ListProduct() {
    val productList = listOf(
        ShopProduct("Lựu đỏ Ai Cập", "155.000đ", "1kg", "Trái cây", 4.9, 120, R.drawable.luu),
        ShopProduct("Combo Trái cây", "250.000đ", "1 set", "Combo", 4.8, 85, R.drawable.begin_img),
        ShopProduct("Táo Envy Mỹ", "55.000đ", "1kg", "Trái cây", 5.0, 200, R.drawable.banner),
        ShopProduct("Dâu Tây Đà Lạt", "80.000đ", "500g", "Đà Lạt", 4.7, 50, R.drawable.luu)
    )

    // Chia danh sách sản phẩm thành từng hàng, mỗi hàng 2 cái
    val rows = productList.chunked(2)

    // 1. Margin ngoài (16.dp) để thẳng hàng với Banner
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.gray_dark),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp) // 2. Padding trong (20.dp) giống Banner
        ) {
            rows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp) // Khoảng cách giữa 2 card
                ) {
                    rowItems.forEach { product ->
                        CardProduct(
                            name = product.name,
                            price = product.price,
                            unit = product.unit,
                            category = product.category,
                            rating = product.rating,
                            soldCount = product.soldCount,
                            imageRes = product.imageRes,
                            showAddButton = true,
                            // 3. Quan trọng: Dùng weight(1f) để ép 2 card chia đều 50/50 màn hình
                            modifier = Modifier.weight(1f)
                        )
                    }
                    
                    // Nếu hàng chỉ có 1 sản phẩm (số lẻ), thêm khoảng trống để card không bị giãn to
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                // Khoảng cách giữa các hàng
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
