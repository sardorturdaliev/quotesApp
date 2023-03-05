package com.sardordev.quotesapp.utils

sealed class ResultEvent<T>(data: T?, message: String?) {

    data class Success<T>(val data: T?) : ResultEvent<T>(data, null)

    data class Error<T>(val message: String?) : ResultEvent<T>(null, message)

}
