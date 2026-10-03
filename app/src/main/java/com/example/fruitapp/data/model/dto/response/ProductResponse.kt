package com.example.fruitapp.data.model.dto.response

import java.util.Date

class ProductResponse (
    val id : Int,
    val name : String,
    val description : String,
    val price : Double,
    val stock: Int,
    val isActive: Boolean,
    val categoryName: String,
    val unit: String,
    val weight: Double,
    val rating: Double,
    val origian: String,
    val packaging: String,
    val freshness: Double,
    val expiryDay: Date,
    val strorageInstruction: String,
    val soldCount: Int,
    val imageUrls: List<String>
)
