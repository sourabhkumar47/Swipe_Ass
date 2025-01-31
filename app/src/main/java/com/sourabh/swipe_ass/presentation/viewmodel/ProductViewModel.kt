package com.sourabh.swipe_ass.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sourabh.swipe_ass.data.local.ProductEntity
import com.sourabh.swipe_ass.domain.repository.ProductRepository
import com.sourabh.swipe_ass.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productsState = mutableStateOf<Resource<List<ProductEntity>>>(Resource.Loading())
    val productsState: State<Resource<List<ProductEntity>>> = _productsState

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            repository.getProducts().collect { result ->
                _productsState.value = result
            }
        }
    }
}