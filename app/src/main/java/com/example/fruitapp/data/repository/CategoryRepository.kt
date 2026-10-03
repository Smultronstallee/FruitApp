package com.example.fruitapp.data.repository

import com.example.fruitapp.data.api.CategoryApi
import com.example.fruitapp.data.model.dto.request.CategoryRequest
import com.example.fruitapp.data.model.dto.response.CategoryResponse

class CategoryRepository(
    private val api: CategoryApi
) {
    //get all categories
    suspend fun getAllCategories() = api.getAllCategories()

    //get category by id
    suspend fun getCategoryById(id: Int) = api.getCategoryById(id)

    //add category
    suspend fun addCategory(req: CategoryRequest): CategoryResponse{
        return api.addCategory(req)
    }

    //update category
    suspend fun updateCategory(id: Int, req: CategoryRequest): CategoryResponse{
        return api.updateCategory(id, req)
    }

    //delete category
    suspend fun deleteCategory(id: Int): Boolean{
        val response = api.deleteCategory(id)
        return response.isSuccessful
    }
}
