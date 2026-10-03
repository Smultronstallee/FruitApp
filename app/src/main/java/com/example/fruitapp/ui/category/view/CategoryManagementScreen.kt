package com.example.fruitapp.ui.category.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R
import com.example.fruitapp.ui.category.component.CardCategory
import com.example.fruitapp.ui.component.admin.HeaderAdmin

@Composable
fun CategoryManagementScreen() {
    var searchQuery by remember { mutableStateOf("") }

    // Dữ liệu mẫu danh sách danh mục
    val categoryList = listOf(
        CategoryItem("Trái cây nội địa", "Các loại trái cây tươi ngon được trồng trong nước", 25, R.drawable.luu),
        CategoryItem("Trái cây nhập khẩu", "Trái cây cao cấp nhập khẩu từ Úc, Mỹ, Nhật Bản", 18, R.drawable.begin_img),
        CategoryItem("Combo quà tặng", "Các giỏ quà trái cây sang trọng cho dịp lễ tết", 10, R.drawable.banner),
        CategoryItem("Rau củ sạch", "Rau củ hữu cơ đạt chuẩn VietGAP", 32, R.drawable.luu)
    )

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(Color.Black)) {
                // Sử dụng HeaderAdmin dùng chung
                HeaderAdmin(adminName = "Admin")
                
                // Section Tìm kiếm
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { 
                            Text(
                                "Tìm kiếm...", 
                                color = colorResource(id = R.color.gray_dark),
                                fontSize = 14.sp
                            ) 
                        },
                        leadingIcon = { 
                            Icon(
                                imageVector = Icons.Default.Search, 
                                contentDescription = null, 
                                tint = colorResource(id = R.color.gray_dark)
                            ) 
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    // Nút thêm danh mục nhanh (tùy chọn)
                    Button(
                        onClick = { /* Xử lý thêm danh mục */ },
                        modifier = Modifier.height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.gray_dark)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                    }
                }
            }
        },
        containerColor = Color.Black // Nền đen toàn màn hình
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            items(categoryList) { category ->
                CardCategory(
                    name = category.name,
                    description = category.description,
                    productCount = category.productCount,
                    imageRes = category.imageRes,
                    onDelete = { /* Xử lý xóa danh mục */ }
                )
            }
        }
    }
}

// Model dữ liệu mẫu
data class CategoryItem(
    val name: String,
    val description: String,
    val productCount: Int,
    val imageRes: Int
)
