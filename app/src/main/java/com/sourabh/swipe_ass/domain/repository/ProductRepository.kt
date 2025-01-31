package com.sourabh.swipe_ass.domain.repository

import com.sourabh.swipe_ass.data.local.ProductEntity
import com.sourabh.swipe_ass.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<Resource<List<ProductEntity>>>
}