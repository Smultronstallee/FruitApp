package com.example.fruitapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.data.model.dto.response.ProductResponse
import com.example.fruitapp.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {
    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products = _products.asStateFlow()

    private val _product = MutableStateFlow<ProductResponse?>(null)
    val product = _product.asStateFlow()
    init {
        loadProducts()
    }
    //method load products
    fun loadProducts(){
        viewModelScope.launch {
            _products.value = repository.getProducts()

        }
    }
    //get product by id
   fun loadProductById(id: Int){
       viewModelScope.launch {
           _product.value = repository.getProductById(id)
       }
   }

    //search product by name
    fun searchProductByName(keyword: String){
        viewModelScope.launch {
            _products.value = repository.searchProductByName(keyword)
        }

    }


}