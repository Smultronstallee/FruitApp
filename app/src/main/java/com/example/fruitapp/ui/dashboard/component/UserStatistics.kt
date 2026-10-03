package com.example.fruitapp.ui.dashboard.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserStatistics() {
    // Dữ liệu mẫu (Số lượng người dùng tăng theo từng thứ)
    val dataPoints = listOf(40f, 90f, 70f, 160f, 120f, 190f, 180f)
    val days = listOf("T2", "T3", "T4", "T5", "T6", "T7", "CN")
    val xLabels = listOf("0", "50", "100", "150", "200")
    val lineColor = Color(0xFF146F72)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Hàng 1: Tiêu đề
        Text(
            text = "Tăng trưởng người dùng",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Hàng 2: Biểu đồ
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            // Trục Y: Thứ (T2, T3, ...) hiển thị bên trái
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 25.dp), // Chừa chỗ cho nhãn trục X
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                days.reversed().forEach { day ->
                    Text(
                        text = day,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.fillMaxSize()) {
                // Vùng vẽ biểu đồ
                Box(modifier = Modifier.weight(1f)) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val width = size.width
                        val height = size.height
                        val maxVal = 200f
                        val spacingY = height / (days.size - 1)

                        // Vẽ các đường lưới dọc tương ứng với 0, 50, 100, 150, 200
                        xLabels.forEachIndexed { index, _ ->
                            val gridX = (index.toFloat() / (xLabels.size - 1)) * width
                            drawLine(
                                color = Color.White.copy(alpha = 0.1f),
                                start = Offset(gridX, 0f),
                                end = Offset(gridX, height),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                        // Vẽ biểu đồ đường (Dữ liệu chạy từ trái sang phải ứng với số lượng)
                        // Trong đó trục dọc là các thứ (T2 ở dưới, CN ở trên hoặc ngược lại)
                        // Ở đây T2 tương ứng index 0 (y thấp nhất), CN tương ứng index 6 (y cao nhất)
                        val path = Path().apply {
                            dataPoints.forEachIndexed { index, value ->
                                val x = (value / maxVal) * width
                                val y = height - (index * spacingY)
                                if (index == 0) moveTo(x, y) else lineTo(x, y)
                            }
                        }

                        drawPath(
                            path = path,
                            color = lineColor,
                            style = Stroke(width = 3.dp.toPx())
                        )

                        // Vẽ các điểm nút trên đường
                        dataPoints.forEachIndexed { index, value ->
                            val x = (value / maxVal) * width
                            val y = height - (index * spacingY)
                            drawCircle(
                                color = lineColor,
                                radius = 5.dp.toPx(),
                                center = Offset(x, y)
                            )
                            drawCircle(
                                color = Color.White,
                                radius = 2.dp.toPx(),
                                center = Offset(x, y)
                            )
                        }
                    }
                }

                // Trục X: Số lượng (0, 50, 100, 150, 200) hiển thị dưới cùng
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    xLabels.forEach { label ->
                        Text(
                            text = label,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
