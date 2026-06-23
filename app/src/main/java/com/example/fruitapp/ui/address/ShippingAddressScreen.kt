package com.example.fruitapp.ui.address

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.ui.address.component.ShippingAddressCard

@Composable
fun ShippingAddressDetail() {
    // 1. Tạo danh sách dữ liệu mẫu để hiển thị
    val addressList = listOf(
        AddressData("Nguyễn Văn A", "0123 456 789", "123 Đường ABC, Phường 1, Quận 1, TP. Hồ Chí Minh", true),
        AddressData("Trần Thị B", "0987 654 321", "456 Đường DEF, Phường 5, Quận Gò Vấp, TP. Hồ Chí Minh", false),
        AddressData("Lê Văn C", "0900 111 222", "789 Đường GHI, Phường 10, Quận 3, TP. Hồ Chí Minh", false)
    )

    Scaffold(
        topBar = {
            // Header: Back Icon + Title + Add Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(horizontal = 4.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* Xử lý quay lại */ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Thông tin giao hàng",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                IconButton(onClick = { /* Xử lý thêm địa chỉ mới */ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Thêm địa chỉ",
                        tint = Color.White
                    )
                }
            }
        },
        containerColor = Color.Black // Nền đen toàn màn hình
    ) { innerPadding ->
        // 2. Hiển thị danh sách bằng LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { 
                Spacer(modifier = Modifier.height(8.dp)) 
            }
            
            items(addressList) { address ->
                // Gọi ShippingAddressCard đã tạo
                ShippingAddressCard(
                    name = address.name,
                    phone = address.phone,
                    address = address.address,
                    isDefault = address.isDefault,
                    onEdit = { /* Xử lý sửa */ },
                    onDelete = { /* Xử lý xóa */ }
                )
            }
        }
    }
}

// Lớp dữ liệu cho địa chỉ
data class AddressData(
    val name: String,
    val phone: String,
    val address: String,
    val isDefault: Boolean
)
