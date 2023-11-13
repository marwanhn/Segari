package com.example.segari.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segari.data.SegariRepository
import com.example.segari.model.OrderItem
import com.example.segari.model.Product
import com.example.segari.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel (
    private val repository: SegariRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderItem>>
        get() = _uiState

    fun getProductById(productId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getProductById(productId))
        }
    }

    fun addToCart(product: Product, count: Int) {
        viewModelScope.launch {
            repository.updateProduct(product.id, count)
        }
    }
}