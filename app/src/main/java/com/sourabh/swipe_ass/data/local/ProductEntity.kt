package com.sourabh.swipe_ass.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val imageUrl: String,
    val price: Double,
    val name: String,
    val type: String,
    val tax: Double,
    val lastUpdated: Long = System.currentTimeMillis()
)