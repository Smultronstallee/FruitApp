package com.example.fruitapp.data.repository

import com.example.fruitapp.data.api.ProductApi
import com.example.fruitapp.data.model.dto.request.ProductRequest
import com.example.fruitapp.data.model.dto.response.ProductResponse

class ProductRepository(
    private val api: ProductApi
) {
    //get all products
    suspend fun getProducts() = api.getAllProducts()

    //get product by id
    suspend fun getProductById(id: Int): ProductResponse{
        return api.getProductById(id)
    }

    //search product by name
    suspend fun searchProductByName(keyword: String)= api.searchProductsByName(keyword)

    //add product
    suspend fun addProduct(req: ProductRequest): ProductResponse{
        return api.addProduct(req)
    }

    //update product
    suspend fun updateProduct(id: Int, req: ProductRequest): ProductResponse{
        return api.updateProduct(id, req)
    }

    //delete product
    suspend fun deleteProduct(id: Int): Boolean{
        val response = api.deleteProduct(id)
        return response.isSuccessful
    }
}