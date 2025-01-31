package com.sourabh.swipe_ass.data.repository

import com.sourabh.swipe_ass.data.local.ProductDao
import com.sourabh.swipe_ass.data.local.ProductEntity
import com.sourabh.swipe_ass.data.remote.mapper.DEFAULT_IMAGE_URL
import com.sourabh.swipe_ass.data.remote.source.ProductRemoteDataSource
import com.sourabh.swipe_ass.domain.repository.ProductRepository
import com.sourabh.swipe_ass.utils.NetworkHelper
import com.sourabh.swipe_ass.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val productDao: ProductDao,
    private val networkHelper: NetworkHelper
) : ProductRepository {

    override fun getProducts(): Flow<Resource<List<ProductEntity>>> = flow {
        try {
            emit(Resource.Loading())

            // First emit cached data
            val cachedProducts = productDao.observeAllProducts().first()
            if (cachedProducts.isNotEmpty()) {
                emit(Resource.Success(cachedProducts))
            }

            // Fetch fresh data if network available
            if (networkHelper.isNetworkConnected()) {
                val remoteProducts = remoteDataSource.getProducts()
                val entities = remoteProducts.map { response ->
                    ProductEntity(
                        imageUrl = response.imageUrl.ifEmpty { DEFAULT_IMAGE_URL }.toString(),
                        price = response.price,
                        name = response.productName,
                        type = response.productType,
                        tax = response.tax
                    )
                }

                // Update local database
                productDao.deleteAllProducts()
                productDao.insertProducts(entities)
            }

            // Continue emitting from local database
            productDao.observeAllProducts().collect { products ->
                if (products.isNotEmpty()) {
                    emit(Resource.Success(products))
                } else {
                    emit(Resource.Error("No data available"))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }
}