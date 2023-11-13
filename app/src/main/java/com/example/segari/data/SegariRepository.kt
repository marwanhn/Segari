package com.example.segari.data

import com.example.segari.model.FakeDataSource
import com.example.segari.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SegariRepository {
    private val orders = mutableListOf<OrderItem>()
    private val banner = mutableListOf<Int>()
    init {
        if (banner.isEmpty()) {
            FakeDataSource.dummyBanners.forEach {
                banner.add(it)
            }
        }
        if (orders.isEmpty()){
            FakeDataSource.dummyProduct.forEach {
                orders.add(OrderItem(it, 0))
            }
        }
    }

    fun getAllProducts(): Flow<List<OrderItem>> {
        return flowOf(orders)
    }

    fun getProductById(productId: Long): OrderItem {
        return orders.first {
            it.item.id == productId
        }
    }

    fun updateProduct(productId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orders.indexOfFirst { it.item.id == productId }
        val result = if (index >= 0) {
            val orderItem = orders[index]
            orders[index] =
                orderItem.copy(item = orderItem.item, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderProducts(): Flow<List<OrderItem>> {
        return getAllProducts()
            .map { orderProducts ->
                orderProducts.filter { orderProduct ->
                    orderProduct.count != 0
                }
            }
    }

    fun searchProducts(query: String): List<OrderItem>{
        return orders.filter {
            it.item.title.contains(query, ignoreCase = true)
        }
    }


    companion object {
        @Volatile
        private var instance: SegariRepository? = null

        fun getInstance(): SegariRepository =
            instance ?: synchronized(this) {
                SegariRepository().apply {
                    instance = this
                }
            }
    }
}