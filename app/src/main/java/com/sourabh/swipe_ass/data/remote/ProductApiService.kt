package com.sourabh.swipe_ass.data.remote

import com.sourabh.swipe_ass.data.remote.model.AddProductResponse
import com.sourabh.swipe_ass.data.remote.model.ProductResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import javax.inject.Inject

interface ProductApiService {
    @GET("public/get")
    suspend fun getProducts(): List<ProductResponse>
}