package com.example.fruitapp.ui.product.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fruitapp.data.model.dto.response.ProductResponse
import com.example.fruitapp.ui.component.admin.HeaderAdmin
import com.example.fruitapp.ui.product.component.AddProduct
import com.example.fruitapp.ui.product.component.CardProduct
import com.example.fruitapp.ui.product.component.DetailProduct
import com.example.fruitapp.ui.product.component.ProductHeader
import com.example.fruitapp.ui.product.viewmodel.ProductViewModel

@Composable
fun ProductManagementScreen(
    viewModel: ProductViewModel = hiltViewModel()
) {
    // Quan sát danh sách sản phẩm từ ViewModel
    val products by viewModel.products.collectAsState()
    
    // Trạng thái hiển thị Popup Chi tiết sản phẩm
    var showDetail by remember { mutableStateOf(false) }
    // Trạng thái chuyển sang màn hình Thêm sản phẩm
    var isAddMode by remember { mutableStateOf(false) }
    // Lưu sản phẩm được chọn để hiển thị trong Detail
    var selectedProduct by remember { mutableStateOf<ProductResponse?>(null) }

    // Tải danh sách sản phẩm khi màn hình mở ra
    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    // 1. Hiển thị Popup DetailProduct nếu showDetail là true
    if (showDetail && selectedProduct != null) {
        DetailProduct(
            pd = selectedProduct!!,
            onDismiss = { 
                showDetail = false 
                selectedProduct = null
            },
            onUpdate = { 
                viewModel.loadProducts() // Refresh lại danh sách sau khi update
                showDetail = false 
            }
        )
    }

    // 2. Kiểm tra chế độ hiển thị
    if (isAddMode) {
        // Màn hình Thêm sản phẩm
        AddProduct(
            viewModel = viewModel,
            onBack = { 
                isAddMode = false 
                viewModel.loadProducts() // Refresh danh sách sau khi thêm
            }
        )
    } else {
        // Màn hình Danh sách sản phẩm
        Scaffold(
            topBar = {
                Column {
                    // Header Admin chung
                    HeaderAdmin(adminName = "Admin")
                    // Header chức năng riêng của Product (Tìm kiếm + Các nút Thêm/Xuất/Nhập)
                    ProductHeader(onAddClick = { isAddMode = true })
                }
            },
            containerColor = Color.Black
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }

                // Hiển thị danh sách sản phẩm thực tế từ DB
                items(products, key = { it.id }) { product ->
                    CardProduct(
                        product = product,
                        onClick = { 
                            selectedProduct = product
                            showDetail = true 
                        },
                        onDelete = { 
                            viewModel.deleteProduct(product.id) {
                                viewModel.loadProducts()
                            }
                        }
                    )
                }
            }
        }
    }
}
