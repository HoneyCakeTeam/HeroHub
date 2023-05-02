package com.example.herohub

sealed class Nour<out T> {
    data class Success<T>(val data: T?) : Nour<T>()
    class Failure(val message: String) : Nour<Nothing>()
    object Loading : Nour<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}
