package com.example.herohub

import com.example.herohub.model.Character
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET("characters")
    fun getAllCharacters() : Single<Response<Character>>
}