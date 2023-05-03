package com.example.herohub.data

import com.example.herohub.data.source.remote.RemoteDataSource
import com.example.herohub.utills.State
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    private val remoteDataSource = RemoteDataSource()
    fun getAllCharacters() = wrapWithState {
        remoteDataSource.getAllCharacters()
    }

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<State<T>> {
        return function().map {
            try {
                if (it.isSuccessful) {
                    State.Success(it.body())
                } else {
                    State.Error(it.message())
                }
            } catch (e: Exception) {
                State.Error(e.message ?: "Connection Failed")
            }
        }
    }
}