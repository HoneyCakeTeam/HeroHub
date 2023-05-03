package com.example.herohub.utills

sealed class UiState<out T> {

    data class Success<T>(val data: T?) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
    object Loading : UiState<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}
