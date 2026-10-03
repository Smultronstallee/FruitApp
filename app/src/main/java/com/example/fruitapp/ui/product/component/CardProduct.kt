package com.example.fruitapp.ui.product.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fruitapp.R
import com.example.fruitapp.data.model.dto.response.ProductResponse
import com.example.fruitapp.ui.product.viewmodel.ProductViewModel

@Composable
fun CardProduct(
    product: ProductResponse,
    onClick: () -> Unit = {},
    onDelete: () -> Unit = {},
) {
    var showDeleteDialog by remember {
        mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color(0xFF574646), RoundedCornerShape(5.dp))
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                /*Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )*/

                val (expiryText, expiryBg) = if (true) {
                    Color(0xFF41B055) to Color.White
                } else {
                    Color(0xFFFF1212) to Color(0xFFF2CBBD)
                }

                Surface(
                    color = expiryBg,
                    shape = RoundedCornerShape(2.dp),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(2.dp)
                ) {
                    Text(
                        text = "Còn hạn",
                        color = expiryText,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = product.name,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${product.price}/${product.unit}",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = "Tồn kho: ${product.stock}",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    IconButton(
                        onClick = { showDeleteDialog = true },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Xóa",
                            tint = Color(0xFFEB3223),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        val (stockText, stockBg) = if (true) {
            Color(0xFFFF1212) to Color(0xFFF5C5C5)
        } else {
            Color(0xFFFF550C) to Color(0xFFFFF5AB)
        }

        Surface(
            color = stockBg,
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 6.dp, y = (-6).dp)
        ) {
            Text(
                text = "Hết hàng",
                color = stockText,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }

        //delete dialog
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Xóa sản phẩm?") },
                text = {
                    Text("Bạn có chắc muốn xóa ${product.name} không?")
                },
                confirmButton = {
                    TextButton(onClick = {
                            showDeleteDialog = false
                            onDelete()

                    }) {
                        Text("Xóa")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDeleteDialog = false
                    }
                    ) {
                        Text("Hủy")
                    }
                }
            )
        }
    }
}

