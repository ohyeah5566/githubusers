package com.example.githubusers.data


/**
 * repository emit data 給 viewModel的資料型態
 */
sealed class BaseResult<out T : Any> {
    data class Error(
        val ex: Throwable
    ) : BaseResult<Nothing>()

    data class Success<T : Any>(
        val data : T
    ) : BaseResult<T>()
}
