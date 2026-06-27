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

data class RecommendedProductData(
    val name: String,
    val price: String,
    val unit: String,
    val category: String,
    val rating: Double,
    val imageRes: Int
)

@Composable
fun RecommendedProduct() {
    val productList = listOf(
        RecommendedProductData("Dưa lưới", "85.000đ", "1kg", "Trái cây", 4.9, R.drawable.luu),
        RecommendedProductData("Măng cụt", "120.000đ", "1kg", "Trái cây", 4.8, R.drawable.begin_img),
        RecommendedProductData("Bơ sáp", "45.000đ", "1kg", "Đà Lạt", 4.7, R.drawable.banner),
        RecommendedProductData("Sầu riêng", "150.000đ", "1kg", "Miền Tây", 5.0, R.drawable.luu)
    )

    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.gray_dark),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(productList) { product ->
                    CardProduct(
                        modifier = Modifier.width(150.dp),
                        name = product.name,
                        price = product.price,
                        unit = product.unit,
                        category = product.category,
                        rating = product.rating,
                        imageRes = product.imageRes
                    )
                }
            }
        }
    }
}
