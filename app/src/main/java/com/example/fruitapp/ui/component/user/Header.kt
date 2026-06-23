package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun Header(
    isHome: Boolean = true,
    title: String = "",
    cartCount: Int? = null,
    showEditAction: Boolean = false,
    showCartIcon: Boolean = true, // Thêm tham số để ẩn/hiện icon giỏ hàng
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    onChatClick: () -> Unit = {},
    onSearchClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("Bạn") }
    var keyword by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isHome) {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = "Chào mừng,", fontSize = 11.sp, color = Color.LightGray)
                Text(text = "$username!", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            }

            BasicTextField(
                value = keyword,
                onValueChange = { keyword = it },
                modifier = Modifier
                    .weight(1f)
                    .height(34.dp)
                    .background(Color.White, RoundedCornerShape(17.dp)),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = colorResource(id = R.color.blue),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            if (keyword.isEmpty()) {
                                Text(text = "Bạn cần tìm gì?", color = Color.Gray, fontSize = 13.sp)
                            }
                            innerTextField()
                        }
                    }
                }
            )
        } else {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Quay lại",
                    tint = Color.White
                )
            }
            
            Text(
                text = buildAnnotatedString {
                    append(title)
                    if (cartCount != null) {
                        withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal)) {
                            append(" ($cartCount)")
                        }
                    }
                },
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f).padding(horizontal = 8.dp)
            )

            if (showEditAction) {
                Text(
                    text = "Sửa",
                    color = colorResource(id = R.color.blue),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onEditClick() }
                        .padding(horizontal = 8.dp)
                )
            } else {
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Tìm kiếm", tint = Color.White)
                }
            }
        }

        // Ẩn hiện icon giỏ hàng dựa trên tham số showCartIcon
        if (showCartIcon) {
            IconButton(onClick = onCartClick) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Giỏ hàng",
                    tint = Color.White,
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        IconButton(onClick = onChatClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Chat,
                contentDescription = "Chat",
                tint = Color.White,
                modifier = Modifier.size(22.dp)
            )
        }
    }
}