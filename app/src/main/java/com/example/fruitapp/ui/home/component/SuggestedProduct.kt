package com.example.fruitapp.ui.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fruitapp.R
import com.example.fruitapp.ui.cart.component.CardProduct

// Định nghĩa dữ liệu cho sản phẩm gợi ý
data class SuggestedProductData(
    val name: String,
    val price: String,
    val unit: String,
    val category: String,
    val rating: Double,
    val reviewCount: Int,
    val tag: String,
    val imageRes: Int
)

@Composable
fun SuggestedProduct() {
    val productList = listOf(
        SuggestedProductData("Táo Envy Mỹ", "55.000đ", "1kg", "Trái cây", 4.9, 120, "Dành cho bạn", R.drawable.begin_img),
        SuggestedProductData("Lựu đỏ Ai Cập", "155.000đ", "1kg", "Nhập khẩu", 4.8, 85, "Gợi ý", R.drawable.luu),
        SuggestedProductData("Combo Fruit", "250.000đ", "1 set", "Combo", 5.0, 200, "Bán chạy", R.drawable.banner),
        SuggestedProductData("Dâu Tây", "80.000đ", "500g", "Đà Lạt", 4.7, 50, "Ưu đãi", R.drawable.luu)
    )

    // 1. Margin ngoài 16.dp đồng bộ với Banner
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.gray_dark),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp) // 2. Padding trong 20.dp đồng bộ Banner
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productList) { product ->
                    CardProduct(
                        name = product.name,
                        price = product.price,
                        unit = product.unit,
                        category = product.category,
                        rating = product.rating,
                        imageRes = product.imageRes,
                        isSuggested = true, // Chế độ gợi ý (hiện tag, ẩn đã bán)
                        tag = product.tag,
                        reviewCount = product.reviewCount,
                        modifier = Modifier.width(150.dp) // Chiều rộng cố định cho LazyRow
                    )
                }
            }
        }
    }
}
