package com.sourabh.swipe_ass.data.remote.model

data class Product(
    val imageUrl: String,
    val price: Double,
    val name: String,
    val type: String,
    val tax: Double
)