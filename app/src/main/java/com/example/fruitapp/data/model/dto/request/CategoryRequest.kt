package com.example.fruitapp.data.model.dto.request

data class CategoryRequest(
    val name: String,
    val image: String?,
    val publicId: String?,
    val description: String
)
