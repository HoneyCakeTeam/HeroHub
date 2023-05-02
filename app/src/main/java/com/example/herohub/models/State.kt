package com.example.herohub.models

sealed class State<out T> {

    data class Success<T>(val data: T) : State<T>()
    data class Erorr(val message: String) : State<Nothing>()
    object Loading : State<Nothing>()

    fun toData(): T? = if (this is Success) data else null
}
