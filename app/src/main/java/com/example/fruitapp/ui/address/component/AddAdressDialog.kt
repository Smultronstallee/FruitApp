package com.example.fruitapp.ui.address.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun AddAddressDialog() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var isDefault by remember { mutableStateOf(false) }
    var province by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var ward by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    
    // State cho loại địa chỉ
    var typeAddress by remember { mutableStateOf("Nhà riêng") }
    val radioOptions = listOf("Nhà riêng", "Công ty", "Khác")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Thêm địa chỉ mới",
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        // Họ và tên
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Họ và tên") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Số điện thoại
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Số điện thoại") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Loại địa chỉ
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Loại địa chỉ",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                radioOptions.forEach { text ->
                    Row(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .selectable(
                                selected = (text == typeAddress),
                                onClick = { typeAddress = text },
                                role = Role.RadioButton
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == typeAddress),
                            onClick = null,
                            colors = RadioButtonDefaults.colors(
                                selectedColor = colorResource(id = R.color.blue)
                            )
                        )
                        Text(
                            text = text,
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Tỉnh/Thành phố, Quận/Huyện, Xã/Phường chung 1 hàng
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = province,
                onValueChange = { province = it },
                label = { Text("Tỉnh/Thành", fontSize = 11.sp) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            OutlinedTextField(
                value = district,
                onValueChange = { district = it },
                label = { Text("Quận/Huyện", fontSize = 11.sp) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
            OutlinedTextField(
                value = ward,
                onValueChange = { ward = it },
                label = { Text("Xã/Phường", fontSize = 11.sp) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Số nhà / Tên đường
        OutlinedTextField(
            value = street,
            onValueChange = { street = it },
            label = { Text("Số nhà / Tên đường") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Checkbox mặc định
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isDefault = !isDefault }
        ) {
            Checkbox(
                checked = isDefault,
                onCheckedChange = { isDefault = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.blue)
                )
            )
            Text(
                text = "Đặt làm địa chỉ mặc định",
                color = Color.Black,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2 Button Hủy và Thêm
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = { /* Xử lý hủy */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, colorResource(id = R.color.blue))
            ) {
                Text(text = "Hủy", color = colorResource(id = R.color.blue))
            }
            Button(
                onClick = { /* Xử lý thêm */ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue)
                )
            ) {
                Text(text = "Thêm")
            }
        }
    }
}
