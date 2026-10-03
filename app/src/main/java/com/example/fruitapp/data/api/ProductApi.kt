package com.example.fruitapp.data.api

import com.example.fruitapp.data.model.dto.request.ProductRequest
import com.example.fruitapp.data.model.dto.response.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    //get all products
    @GET("api/products")
    suspend fun getAllProducts(): List<ProductResponse>

    //get product by id
    suspend fun getProductById(
        @Path("id") id: Int
    ): ProductResponse

    //search product by name
    @GET("products/search")
    suspend fun searchProductsByName(
        @Query("keyword") keyword: String
    ): List<ProductResponse>

    //add product
    @POST("api/products")
    suspend fun addProduct(
        @Body req: ProductRequest): ProductResponse

    //update product
    @PUT("api/products/{id}")
    suspend fun updateProduct(
        @Path("id") id : Int,
        @Body req: ProductRequest
    ): ProductResponse

    //delete product
    @DELETE("api/products/{id}")
    suspend fun deleteProduct(
        @Path("id") id: Int
    ): Response<Unit>
}