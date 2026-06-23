package com.example.fruitapp.ui.address.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fruitapp.R

@Composable
fun ShippAddressSection(){
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .background(color = colorResource( id= R.color.gray_dark))) {

        //1. xem tất cả
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text("Thông tin giao hàng", modifier = Modifier.weight(1f),
                color = Color.White, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.Bottom) {
                Text("Xem tất cả", color = Color.White, fontSize = 10.sp)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.White)
            }
        }

        //2. thông tin
            Column() {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text("$name", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Text("$phone", color = Color.White, fontSize = 10.sp)
                }
                Text("$address", color = Color.White, fontSize = 14.sp)

        }
    }
}