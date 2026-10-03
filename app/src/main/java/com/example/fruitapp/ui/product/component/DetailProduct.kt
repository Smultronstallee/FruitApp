package com.example.fruitapp.ui.product.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.fruitapp.data.model.dto.response.ProductResponse
import com.example.fruitapp.ui.product.viewmodel.ProductViewModel

@Composable
fun DetailProduct(
    pd: ProductResponse,
    onDismiss: () -> Unit = {},
    onUpdate: () -> Unit = {}
) {
    var productName by remember(pd.id) { mutableStateOf(pd.name) }
    var productDesc by remember(pd.id) { mutableStateOf(pd.description?:"") }
    var weight by remember(pd.id) { mutableStateOf(pd.weight.toString()) }
    var price by remember(pd.id) { mutableStateOf(pd.price.toString()) }
    var stock by remember(pd.id) { mutableStateOf(pd.stock.toString()) }
    var origin by remember(pd.id) { mutableStateOf(pd.origian) }
    var packaging by remember(pd.id) { mutableStateOf(pd.packaging) }
    var freshness by remember(pd.id) { mutableStateOf(pd.freshness.toString()) }
    var expiryDay by remember(pd.id) { mutableStateOf(pd.expiryDay.toString()) }
    var strorageInstruction by remember(pd.id) { mutableStateOf(pd.strorageInstruction) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(16.dp)),
            color = Color.White
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Chi tiết sản phẩm",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(Icons.Default.Close, contentDescription = "Đóng", tint = Color.Black)
                    }
                }

                HorizontalDivider(thickness = 1.dp, color = Color(0xFFF0F0F0))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Column {
                        Text("Tên sản phẩm", fontSize = 14.sp, color = Color.Black)
                        DetailTextField(value = productName, onValueChange = { productName = it })
                    }

                    Column {
                        Text("Mô tả sản phẩm", fontSize = 14.sp, color = Color.Black)
                        DetailTextField(
                            value = productDesc,
                            onValueChange = {
                                if (it.length <= 50) productDesc = it
                            },
                            modifier = Modifier.height(80.dp),
                            singleLine = false
                        )
                        Text(
                            text = "${productDesc.length}/50",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                    Text(
                        text = "Mô tả chi tiết",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Danh mục", fontSize = 14.sp, color = Color.Black)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp)
                                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(pd.categoryName?: "Chưa có danh mục", color = Color.Black, fontSize = 14.sp)
                                    Icon(Icons.Default.KeyboardArrowDown, null, tint = Color.Black)
                                }
                            }
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Trọng lượng", fontSize = 14.sp, color = Color.Black)
                            DetailTextField(value = weight, onValueChange = { weight = it })
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Giá", fontSize = 14.sp, color = Color.Black)
                            DetailTextField(value = price, onValueChange = { price = it })
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Tồn kho", fontSize = 14.sp, color = Color.Black)
                            DetailTextField(value = stock, onValueChange = { stock = it })
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Xuất xứ", fontSize = 14.sp, color = Color.Black)
                            DetailTextField(value = origin, onValueChange = { origin = it })
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Đóng gói", fontSize = 14.sp, color = Color.Black)
                            DetailTextField(value = packaging, onValueChange = { packaging = it })
                        }
                    }

                    Column {
                        Text("Độ tươi", fontSize = 14.sp, color = Color.Black)
                        DetailTextField(value = freshness, onValueChange = { freshness = it })
                    }

                    Column {
                        Text("HSD", fontSize = 14.sp, color = Color.Black)
                        DetailTextField(value = expiryDay, onValueChange = { expiryDay = it })
                    }

                    Column {
                        Text("Bảo quản", fontSize = 14.sp, color = Color.Black)
                        DetailTextField(value = strorageInstruction, onValueChange = { strorageInstruction = it })
                    }

                    Column {
                        Text("Thêm ảnh", fontSize = 14.sp, color = Color.Black, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                                .background(Color(0xFFF9F9F9)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = Icons.Default.AddAPhoto,
                                    contentDescription = "Thêm ảnh",
                                    tint = Color(0xFF574646),
                                    modifier = Modifier.size(32.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Thêm tối đa 5 ảnh",
                                    fontSize = 12.sp,
                                    color = Color(0xFF574646)
                                )
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFFD9D9D9)),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                    ) {
                        Text("Hủy", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = onUpdate,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F93B3))
                    ) {
                        Text("Cập nhật", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        singleLine = singleLine,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedBorderColor = Color(0xFF2F93B3),
            unfocusedBorderColor = Color.LightGray
        )
    )
}
