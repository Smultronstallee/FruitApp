package com.example.fruitapp.ui.category.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.data.model.dto.request.CategoryRequest
import com.example.fruitapp.data.model.dto.response.CategoryResponse
import com.example.fruitapp.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val respository: CategoryRepository
): ViewModel() {
    var categories = MutableStateFlow<List<CategoryResponse>>(emptyList())

    var isLoading by mutableStateOf(false)
    private set

    var errorMessage by mutableStateOf<String?>(null)
    private set

    //get all categories
    fun getAllCategories(){
        viewModelScope.launch {
            try{
                isLoading = true
                errorMessage = null
                categories.value = respository.getAllCategories()
            }catch (e: Exception){
                errorMessage = e.message ?: "Không thể tải danh mục"
            }finally {
                isLoading = false
            }
        }
    }

    //add product
    fun addCategory(
        req: CategoryRequest,
        onSuccess:()-> Unit
    ){
        viewModelScope.launch {
            try{
                isLoading = true
                errorMessage = null
                respository.addCategory(req)
                onSuccess()
            }catch (e: Exception){
                errorMessage = e.message ?: "Không thể thêm danh mục"
            }finally {
                isLoading = false
            }
        }
    }

    //update category
    fun updateCategory(
        id: Int,
        req: CategoryRequest,
        onSuccess: () -> Unit){
        viewModelScope.launch {
            try{
                isLoading = true
                errorMessage = null
                respository.updateCategory(id, req)
                onSuccess()
            }catch (e: Exception){
                errorMessage = e.message ?: "Không thể cập nhật danh mục"
            }finally {
                isLoading = false
            }
        }
    }

    //delete category
    fun deleteCategory(
        id: Int,
        onSuccess: () -> Unit
    ){
        viewModelScope.launch {
            try{
                isLoading = true
                errorMessage = null
                respository.deleteCategory(id)
                onSuccess()
            }catch (e: Exception){
                errorMessage = e.message ?: "Không thể xóa danh mục"
            }finally {
                isLoading = false
            }
        }
    }

    //load category
    fun loadCategories(){
        viewModelScope.launch {
            try{
                isLoading = true
                val _categories = respository.getAllCategories()
                categories.value = _categories
            }catch (e: Exception){
                errorMessage = e.message ?: "Không thể tải danh mục"
            }finally {
                isLoading = false
            }
            }
        }
    }