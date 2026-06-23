package com.example.fruitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.fruitapp.ui.address.ShippingAddressDetail
import com.example.fruitapp.ui.address.component.AddAddressDialog
import com.example.fruitapp.ui.cart.CartScreen
import com.example.fruitapp.ui.navigation.AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            val navController = rememberNavController()
            AppNavGraph(navController)
        }
    }
}