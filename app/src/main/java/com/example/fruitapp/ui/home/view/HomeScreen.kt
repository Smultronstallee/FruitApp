package com.example.fruitapp.ui.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R
import com.example.fruitapp.ui.component.user.*
import com.example.fruitapp.ui.home.component.BestSeller
import com.example.fruitapp.ui.home.component.FlashSale
import com.example.fruitapp.ui.home.component.SuggestedProduct

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { Footer() },
        containerColor = colorResource(id = R.color.black) // Nền đen toàn trang
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // Cho phép cuộn trang
        ) {
            // 1. Header (Chào hỏi & Tìm kiếm)
            Header()

            // 2. Banner Khuyến mãi
            Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                Banner()
            }

            // 3. Flash Sale
            SectionTitle("Giảm giá sốc")
            FlashSale()

            // 4. Bán chạy nhất
            SectionTitle("Sản phẩm bán chạy")
            BestSeller()

            // 5. Gợi ý cho bạn
            SectionTitle("Gợi ý dành cho bạn")
            SuggestedProduct()

            // 6. Danh sách sản phẩm (Có nút thêm vào giỏ)
            SectionTitle("Tất cả sản phẩm")
            ListProduct()

            // Khoảng trống cuối trang để không bị Footer che
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 16.dp, top = 24.dp, bottom = 12.dp)
            .fillMaxWidth()
    )
}