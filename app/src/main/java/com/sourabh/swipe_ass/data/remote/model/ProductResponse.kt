package com.sourabh.swipe_ass.data.remote.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("image") val imageUrl: String,
    @SerializedName("price") val price: Double,
    @SerializedName("product_name") val productName: String,
    @SerializedName("product_type") val productType: String,
    @SerializedName("tax") val tax: Double
)

// data/remote/model/AddProductResponse.kt
data class AddProductResponse(
    @SerializedName("message") val message: String,
    @SerializedName("product_details") val productDetails: ProductResponse,
    @SerializedName("product_id") val productId: Int,
    @SerializedName("success") val success: Boolean
)