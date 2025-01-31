package com.sourabh.swipe_ass.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}