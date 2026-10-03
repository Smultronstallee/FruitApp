package com.example.fruitapp.ui.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.data.model.dto.request.ProductRequest
import com.example.fruitapp.ui.product.viewmodel.ProductViewModel

@Composable
fun AddProduct(
    viewModel: ProductViewModel,
    onBack: () -> Unit
) {
    var productName by remember { mutableStateOf("") }
    var productDesc by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var origin by remember { mutableStateOf("") }
    var freshness by remember { mutableStateOf("") }
    var strorageInstruction by remember { mutableStateOf("") }
    var expiryDay by remember { mutableStateOf("") }
    var packaging by remember { mutableStateOf("") }

    val darkGray = Color(0xFF574646)
    val blueColor = Color(0xFF2F93B3)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Handle back */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Thêm sản phẩm",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(darkGray)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tên sản phẩm
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tên sản phẩm", color = Color.White, modifier = Modifier.width(100.dp), fontSize = 14.sp)
                CustomTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    modifier = Modifier.weight(1f)
                )
            }

            // Mô tả sản phẩm
            Column {
                Text("Mô tả sản phẩm", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                CustomTextField(
                    value = productDesc,
                    onValueChange = { if (it.length <= 50) productDesc = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    singleLine = false
                )
                Text(
                    "${productDesc.length}/50",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.End)
                )
            }

            // Danh mục
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Danh mục", color = Color.White, modifier = Modifier.width(100.dp), fontSize = 14.sp)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Chọn danh mục", color = Color.Black, fontSize = 14.sp)
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Black)
                    }
                }
            }

            // Giá
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Giá", color = Color.White, modifier = Modifier.width(100.dp), fontSize = 14.sp)
                CustomTextField(
                    value = price,
                    onValueChange = { price = it },
                    modifier = Modifier.weight(1f)
                )
            }

            // Tồn kho
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Tồn kho", color = Color.White, modifier = Modifier.width(100.dp), fontSize = 14.sp)
                CustomTextField(
                    value = stock,
                    onValueChange = { stock = it },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // PHẦN 3: Chi tiết sản phẩm
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(darkGray)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                "Chi tiết sản phẩm",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            // Đơn vị bán
            Column {
                Text("Đơn vị bán", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Chọn đơn vị (kg, g...)", color = Color.Black, fontSize = 14.sp)
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = Color.Black)
                    }
                }
            }

            // Trọng lượng | Xuất xứ
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Trọng lượng", color = Color.White, fontSize = 12.sp)
                    CustomTextField(value = weight, onValueChange = { weight = it })
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Xuất xứ", color = Color.White, fontSize = 12.sp)
                    CustomTextField(value = origin, onValueChange = { origin = it })
                }
            }

            // Độ tươi | Bảo quản
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Độ tươi", color = Color.White, fontSize = 12.sp)
                    CustomTextField(
                        value = freshness,
                        onValueChange = { freshness = it },
                        placeholder = "Vd: 80-85%"
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Bảo quản", color = Color.White, fontSize = 12.sp)
                    CustomTextField(value = strorageInstruction, onValueChange = { strorageInstruction = it })
                }
            }

            // HSD | Đóng gói
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("HSD", color = Color.White, fontSize = 12.sp)
                    CustomTextField(
                        value = expiryDay,
                        onValueChange = { expiryDay = it },
                        placeholder = "VD: 5-7 ngày"
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Đóng gói", color = Color.White, fontSize = 12.sp)
                    CustomTextField(value = packaging, onValueChange = { packaging = it })
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Thêm ảnh", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = "Add photo",
                        tint = darkGray,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Thêm tối đa 5 ảnh", color = darkGray, fontSize = 12.sp)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { /* Handle cancel */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Hủy", color = Color.Black)
            }
            Button(
                onClick = {
                    val request = ProductRequest(
                        name=productName,
                        description=productDesc,
                        price=price.toDouble(),
                        stock=stock.toInt(),
                        weight=weight.toDouble(),
                        origin=origin,
                        freshness=freshness.toDouble(),
                        strorageInstruction = strorageInstruction,
                        expiryDay=expiryDay,
                        packaging=packaging,

                    )
                viewModel.addProduct(request)

                    )
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = blueColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Lưu", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        shape = RoundedCornerShape(4.dp)
    )
}
