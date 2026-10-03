package com.example.fruitapp.data.api

import com.example.fruitapp.data.model.dto.request.CategoryRequest
import com.example.fruitapp.data.model.dto.response.CategoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoryApi {
    //get all categories
    @GET("api/categories")
    suspend fun getAllCategories(): List<CategoryResponse>

    //get category by id
    @GET("api/categories/{id}")
    suspend fun getCategoryById(
        @Path("id") id: Int
    ): CategoryResponse

    //add category
    @POST("categories")
    suspend fun addCategory(
        @Body request: CategoryRequest
    ): CategoryResponse

    //update ccategory
    @PUT("categories/{id}")
    suspend fun updateCategory(
        @Path("id") id: Int,
        @Body request: CategoryRequest
    ): CategoryResponse

    //delete category
    @DELETE("categories/{id}")
    suspend fun deleteCategory(
        @Path("id") id: Int
    ): Response<Unit>
}