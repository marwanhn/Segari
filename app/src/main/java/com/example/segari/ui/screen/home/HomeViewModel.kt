package com.example.segari.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segari.data.SegariRepository
import com.example.segari.model.OrderItem
import com.example.segari.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: SegariRepository

) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderItem>>>
        get() = _uiState

    fun getAllProducts() {
        viewModelScope.launch {
            repository.getAllProducts()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderProducts ->
                    _uiState.value = UiState.Success(orderProducts)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            try {
                val searchResult = repository.searchProducts(_query.value)
                _uiState.value = UiState.Success(searchResult)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

}