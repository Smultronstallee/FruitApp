package com.example.fruitapp.ui.component.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R
import androidx.compose.ui.res.colorResource

@Composable
fun Banner() {

    var titlePromotion by remember {
        mutableStateOf("ƯU ĐÃI CHO MỘT SỐ SẢN PHẨM LÊN ĐẾN 20%")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.gray_dark),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(2f)
        ) {

            // TITLE
            Text(
                text = buildAnnotatedString {
                    append("ƯU ĐÃI CHO MỘT SỐ SẢN PHẨM LÊN ĐẾN ")
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF2F93B3),
                            fontSize = 20.sp
                        )
                    ) {
                        append("20%")
                    }
                },
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            // BUTTON GRADIENT
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF2F93B3), // Đã sửa: 0xFF thay cho #
                                Color(0xFF143F4D)  // Đã sửa: 0xFF thay cho #
                            )
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable {
                        // Xử lý khi click
                    }
                    .padding(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Xem thêm tất cả sản phẩm",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 2. IMAGE
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "Banner image",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}