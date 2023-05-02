package com.example.herohub

import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    fun getAllCharacters() = wrapWithState {
        MarvelApi.marvelService.getAllCharacters()
    }

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<Nour<T>> {
        return function().map {
            if (it.isSuccessful) {
                Nour.Success(it.body())
            } else {
                Nour.Failure(it.message())
            }
        }
    }
}