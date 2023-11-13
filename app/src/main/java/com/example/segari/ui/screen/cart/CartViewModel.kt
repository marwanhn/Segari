package com.example.segari.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segari.data.SegariRepository
import com.example.segari.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel (
    private val repository: SegariRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderProducts()
                .collect { orderProduct ->
                    val totalPrice =
                        orderProduct.sumOf { it.item.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderProduct, totalPrice))
                }
        }
    }

    fun updateOrderProduct(productId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateProduct(productId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderProducts()
                    }
                }
        }
    }
}