package com.sourabh.swipe_ass.data.remote.mapper

import com.sourabh.swipe_ass.data.remote.model.Product
import com.sourabh.swipe_ass.data.remote.model.ProductResponse

fun ProductResponse.toDomainModel(): Product {
    return Product(
        imageUrl = imageUrl.ifEmpty { DEFAULT_IMAGE_URL },
        price = price,
        name = productName,
        type = productType,
        tax = tax
    )
}

const val DEFAULT_IMAGE_URL = "https://example.com/default-image.png"