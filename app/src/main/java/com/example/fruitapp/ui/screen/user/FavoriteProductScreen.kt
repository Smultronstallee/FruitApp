package com.example.fruitapp.ui.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R
import com.example.fruitapp.ui.cart.component.CardProduct
import com.example.fruitapp.ui.component.user.Footer
import com.example.fruitapp.ui.component.user.Header
import com.example.fruitapp.ui.home.component.RecommendedProduct

@Composable
fun FavoriteProductScreen() {
    // 1. Dữ liệu mẫu sản phẩm yêu thích
    val favoriteProducts = listOf(
        FavoriteProductData("Táo Envy Mỹ", "55.000đ", "1kg", "Trái cây", 4.9, R.drawable.luu),
        FavoriteProductData("Lựu đỏ Ai Cập", "155.000đ", "1kg", "Trái cây", 4.8, R.drawable.luu),
        FavoriteProductData("Dâu Tây Đà Lạt", "80.000đ", "500g", "Đà Lạt", 4.7, R.drawable.luu),
        FavoriteProductData("Cam Sành", "35.000đ", "1kg", "Nội địa", 4.5, R.drawable.begin_img)
    )

    Scaffold(
        bottomBar = { Footer() },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            // 2. Header chung
            Header(
                isHome = false,
                title = "Sản phẩm yêu thích",
                onBackClick = { /* Xử lý quay lại */ }
            )

            // 3. Danh sách nội dung (Yêu thích + Gợi ý)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }

                // --- PHẦN 1: DANH SÁCH YÊU THÍCH (2 CỘT) ---
                items(favoriteProducts.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowItems.forEach { product ->
                            CardProduct(
                                modifier = Modifier.weight(1f),
                                name = product.name,
                                price = product.price,
                                unit = product.unit,
                                category = product.category,
                                rating = product.rating,
                                imageRes = product.imageRes,
                                showAddButton = true
                            )
                        }
                        if (rowItems.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                // --- PHẦN 2: TIÊU ĐỀ GỢI Ý ---
                item {
                    Text(
                        text = "Sản phẩm gợi ý",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                // --- PHẦN 3: COMPONENT GỢI Ý (CUỘN NGANG) ---
                item {
                    RecommendedProduct()
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}

data class FavoriteProductData(
    val name: String,
    val price: String,
    val unit: String,
    val category: String,
    val rating: Double,
    val imageRes: Int
)
