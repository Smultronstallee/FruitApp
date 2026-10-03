package com.example.fruitapp.data.model.dto.request

import java.util.Date

data class ProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int,
    val category: String,
    val imageUrl: String,
    val unit: String,
    val weight: Double,
    val rating: Double,
    val origin: String,
    val packaging: String,
    val freshness: Double,
    val expiryDay: Date,
    val strorageInstruction: String,
    val stock: Int,
)
