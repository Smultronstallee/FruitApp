package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fruitapp.R
import com.example.fruitapp.data.model.dto.response.ProductResponse
import com.example.fruitapp.ui.cart.component.CardProduct

@Composable
fun ListProduct(
    products: List<ProductResponse>
) {


    // Chia danh sách sản phẩm thành từng hàng, mỗi hàng 2 cái
    val rows = products.chunked(2)

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
                            price = "${product.price}đ",
                            unit = product.unit,
                            category = product.categoryName,
                            rating = product.rating,
                            soldCount = product.soldCount,
                            imageUrl = product.imageUrls.firstOrNull(),
                            showAddButton = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
