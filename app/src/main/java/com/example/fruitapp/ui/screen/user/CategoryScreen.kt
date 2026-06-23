package com.example.fruitapp.ui.screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R
import com.example.fruitapp.ui.cart.component.CardProduct
import com.example.fruitapp.ui.component.user.Footer
import com.example.fruitapp.ui.component.user.Header

// Model cho sản phẩm trong trang Category
data class CategoryProduct(
    val name: String,
    val price: String,
    val unit: String,
    val category: String,
    val rating: Double,
    val imageRes: Int,
    val soldCount: Int = 100
)

@Composable
fun CategoryScreen() {
    var selectedCategory by remember { mutableStateOf("Tất cả") }
    var expanded by remember { mutableStateOf(false) }
    val sortOptions = listOf("Mới nhất", "Giá thấp - cao", "Giá cao - thấp")
    var selectedSort by remember { mutableStateOf(sortOptions[0]) }

    val productList = listOf(
        CategoryProduct("Táo Envy Mỹ", "55.000đ", "1kg", "Trái cây", 4.9, R.drawable.luu),
        CategoryProduct("Lựu đỏ Ai Cập", "155.000đ", "1kg", "Trái cây", 4.8, R.drawable.luu),
        CategoryProduct("Combo Fruit", "250.000đ", "1 set", "Combo", 5.0, R.drawable.banner),
        CategoryProduct("Dâu Tây", "80.000đ", "500g", "Đà Lạt", 4.7, R.drawable.luu),
        CategoryProduct("Cam Sành", "35.000đ", "1kg", "Nội địa", 4.5, R.drawable.begin_img),
        CategoryProduct("Nho Móng Tay", "180.000đ", "1kg", "Nhập khẩu", 4.9, R.drawable.luu)
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
            // 1. Gọi Header dùng chung với cấu hình cho trang Danh mục
            Header(
                isHome = false,
                title = "Danh mục sản phẩm",
                onBackClick = { /* Xử lý quay lại */ },
                onSearchClick = { /* Xử lý tìm kiếm */ },
                onCartClick = { /* Xử lý giỏ hàng */ },
                onChatClick = { /* Xử lý chat */ }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 2. Horizontal Category List
                item {
                    val categories = listOf(
                        CategoryData("Tất cả", icon = Icons.Default.GridView),
                        CategoryData("Nội địa", imageRes = R.drawable.begin_img),
                        CategoryData("Nhập khẩu", imageRes = R.drawable.luu),
                        CategoryData("Combo", imageRes = R.drawable.banner),
                        CategoryData("Nước ép", imageRes = R.drawable.luu),
                        CategoryData("Sấy khô", imageRes = R.drawable.begin_img),
                        CategoryData("Rau củ", imageRes = R.drawable.banner)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(categories) { category ->
                            CategorySmallCard(
                                category = category,
                                isSelected = selectedCategory == category.name,
                                onClick = { selectedCategory = category.name }
                            )
                        }
                    }
                }

                // 3. Sort Section
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box {
                            Row(
                                modifier = Modifier
                                    .clickable { expanded = true }
                                    .padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = selectedSort,
                                    color = Color.LightGray,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Sort,
                                    contentDescription = "Sắp xếp",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                modifier = Modifier.background(colorResource(id = R.color.gray_dark))
                            ) {
                                sortOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = option,
                                                color = if (selectedSort == option) colorResource(id = R.color.blue) else Color.White,
                                                fontSize = 14.sp
                                            )
                                        },
                                        onClick = {
                                            selectedSort = option
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                // 4. Product Grid (2 columns)
                items(productList.chunked(2)) { rowProducts ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        for (product in rowProducts) {
                            CardProduct(
                                modifier = Modifier.weight(1f),
                                name = product.name,
                                price = product.price,
                                unit = product.unit,
                                category = product.category,
                                rating = product.rating,
                                imageRes = product.imageRes,
                                soldCount = product.soldCount,
                                showAddButton = true
                            )
                        }
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
fun CategorySmallCard(
    category: CategoryData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .height(36.dp)
            .clickable { onClick() },
        shape = CircleShape,
        color = if (isSelected) colorResource(id = R.color.blue) else colorResource(id = R.color.white)
    ) {
        Row(
            modifier = Modifier.padding(start = 6.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (category.imageRes != null) {
                Image(
                    painter = painterResource(id = category.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } else if (category.icon != null) {
                Box(
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White.copy(alpha = 0.2f) else Color.LightGray.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (isSelected) Color.White else colorResource(id = R.color.blue)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = category.name,
                color = if (isSelected) Color.White else Color.Black,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                maxLines = 1
            )
        }
    }
}

data class CategoryData(
    val name: String,
    val imageRes: Int? = null,
    val icon: ImageVector? = null
)
