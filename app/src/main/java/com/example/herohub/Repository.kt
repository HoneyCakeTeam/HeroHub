package com.example.herohub

import com.example.herohub.model.Character
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    fun getAllCharacters(): Single<Response<Character>> {
        return MarvelApi.marvelService.getAllCharacters()
    }
}