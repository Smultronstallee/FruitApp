package com.example.fruitapp.ui.product.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.data.model.dto.request.ProductRequest
import com.example.fruitapp.data.model.dto.response.ProductResponse
import com.example.fruitapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val respository: ProductRepository
): ViewModel() {
    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products: StateFlow<List<ProductResponse>> = _products.asStateFlow()

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    //add product
    fun addProduct(
        req: ProductRequest,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                respository.addProduct(req)
                onSuccess()
                loadProducts() // Refresh list
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    //update product
    fun updateProduct(
        id: Int,
        req: ProductRequest,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                respository.updateProduct(id, req)
                onSuccess()
                loadProducts() // Refresh list
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    //delete product
    fun deleteProduct(
        id: Int,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                respository.deleteProduct(id)
                onSuccess()
                loadProducts() // Refresh list
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    //load product
    fun loadProducts() {
        viewModelScope.launch {
            try {
                isLoading = true
                val products = respository.getProducts()
                _products.value = products
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}