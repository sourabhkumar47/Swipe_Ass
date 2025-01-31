package com.sourabh.swipe_ass.data.remote.source

import com.sourabh.swipe_ass.data.remote.ProductApiService
import com.sourabh.swipe_ass.data.remote.model.ProductResponse
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val apiService: ProductApiService
) {
    suspend fun getProducts(): List<ProductResponse> {
        return apiService.getProducts()
    }
}