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

data class BestSellerProduct(
    val name: String,
    val price: String,
    val category: String,
    val rating: Double,
    val soldCount: Int,
    val imageRes: Int
)

@Composable
fun BestSeller() {
    val productList = listOf(
        BestSellerProduct("Lựu đỏ Ai Cập", "155.000đ", "Trái cây", 4.9, 120, R.drawable.luu),
        BestSellerProduct("Combo Trái cây", "250.000đ", "Combo", 4.8, 85, R.drawable.begin_img),
        BestSellerProduct("Táo Envy Mỹ", "55.000đ", "Trái cây", 5.0, 200, R.drawable.banner),
        BestSellerProduct("Dâu Tây Đà Lạt", "80.000đ", "Đà Lạt", 4.7, 50, R.drawable.luu)
    )

    // 1. Margin ngoài 16.dp đồng bộ với Banner và SuggestedProduct
    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.gray_dark),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp) // 2. Padding trong 20.dp
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productList) { product ->
                    CardProduct(
                        // 3. Cố định width 150.dp để giống SuggestedProduct, giúp hiển thị ~2 card
                        modifier = Modifier.width(150.dp),
                        name = product.name,
                        price = product.price,
                        category = product.category,
                        rating = product.rating,
                        soldCount = product.soldCount,
                        imageRes = product.imageRes
                    )
                }
            }
        }
    }
}
