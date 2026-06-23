package com.example.fruitapp.ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R
import com.example.fruitapp.ui.cart.component.CartCard
import com.example.fruitapp.ui.component.user.Header

@Composable
fun CartScreen() {
    var isEditing by remember { mutableStateOf(false) }
    var isSelectAll by remember { mutableStateOf(false) }

    // Dữ liệu mẫu giỏ hàng
    val cartItems = listOf(
        CartItemData("Lựu đỏ Ai Cập Ngon lăm sa", "Trái cây", "55.000đ", R.drawable.luu, 2),
        CartItemData("Táo Envy Mỹ", "Trái cây", "155.000đ", R.drawable.begin_img, 1),
        CartItemData("Combo Fruit", "Combo", "250.000đ", R.drawable.banner, 1)
    )

    Scaffold(
        bottomBar = {
            CartSpecificFooter(
                isSelectAll = isSelectAll,
                onSelectAllClick = { isSelectAll = !isSelectAll },
                totalPrice = "460.000đ"
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            // 1. Header: Xóa icon giỏ hàng bằng showCartIcon = false
            Header(
                isHome = false,
                title = "Giỏ hàng",
                cartCount = cartItems.size,
                showEditAction = true,
                showCartIcon = false, // ẨN ICON GIỎ HÀNG
                onBackClick = { /* Quay lại */ },
                onEditClick = { isEditing = !isEditing },
                onChatClick = { /* Chat */ }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(cartItems) { item ->
                    CartCard(
                        name = item.name,
                        category = item.category,
                        price = item.price,
                        imageRes = item.imageRes,
                        onDelete = { /* Xử lý xóa */ }
                    )
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
fun CartSpecificFooter(
    isSelectAll: Boolean,
    onSelectAllClick: () -> Unit,
    totalPrice: String
) {
    Surface(
        color = colorResource(id = R.color.gray_dark),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Bên trái: Ô vuông bo tròn 5dp chọn tất cả
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { onSelectAllClick() }
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            if (isSelectAll) colorResource(id = R.color.blue) else Color.Transparent,
                            RoundedCornerShape(5.dp) // BO TRÒN 5DP
                        )
                        .border(
                            width = 2.dp,
                            color = if (isSelectAll) colorResource(id = R.color.blue) else Color.White,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelectAll) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Tất cả", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }

            // Bên phải: Giá và Nút mua hàng
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = totalPrice,
                    color = Color.Red,
                    fontSize = 16.sp,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* Mua hàng */ },
                    modifier = Modifier
                        .height(44.dp)
                        .width(110.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue))
                ) {
                    Text(text = "Mua hàng", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }
}

data class CartItemData(
    val name: String,
    val category: String,
    val price: String,
    val imageRes: Int,
    val quantity: Int
)
