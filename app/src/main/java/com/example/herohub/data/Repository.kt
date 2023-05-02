package com.example.herohub.data

import com.example.herohub.data.source.remote.MarvelApi
import com.example.herohub.utills.State
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    fun getAllCharacters() = wrapWithState {
        MarvelApi.marvelService.getAllCharacters()
    }

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<State<T>> {
        return function().map {
            if (it.isSuccessful) {
                State.Success(it.body())
            } else {
                State.Error(it.message())
            }
        }
    }
}