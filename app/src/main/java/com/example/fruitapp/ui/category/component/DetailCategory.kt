package com.example.fruitapp.ui.category.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.fruitapp.R

@Composable
fun DetailCategory(
    onDismiss: () -> Unit = {},
    onUpdate: (String, String) -> Unit = { _, _ -> }
) {
    var categoryName by remember { mutableStateOf("Trái cây nội địa") }
    var description by remember { mutableStateOf("Các loại trái cây tươi ngon trong nước") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Chi tiết danh mục",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }
                }

                // Body
                // Hàng 1: Tên danh mục
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Tên danh mục",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = categoryName,
                        onValueChange = { categoryName = it },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true
                    )
                }

                // Hàng 2: Mô tả
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Mô tả",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    OutlinedTextField(
                        value = description,
                        onValueChange = { if (it.length <= 50) description = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        supportingText = {
                            Text(
                                text = "${description.length}/50",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                                color = Color.Gray
                            )
                        }
                    )
                }

                // Hàng 4: Thêm hình ảnh
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Thêm hình ảnh",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .border(1.dp, Color(0xFFD9D9D9), RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { /* Handle image pick */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.AddAPhoto,
                                contentDescription = "Add Photo",
                                tint = colorResource(id = R.color.gray_dark),
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Chỉ được thêm 1 ảnh",
                                color = colorResource(id = R.color.gray_dark),
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Hàng 5: Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Hủy", color = Color.Black)
                    }
                    Button(
                        onClick = { onUpdate(categoryName, description) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2F93B3),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = "Cập nhật")
                    }
                }
            }
        }
    }
}
