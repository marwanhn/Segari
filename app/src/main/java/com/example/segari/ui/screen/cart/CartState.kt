package com.example.segari.ui.screen.cart

import com.example.segari.model.OrderItem

data class CartState(
    val orderProduct: List<OrderItem>,
    val totalPrice: Int
)