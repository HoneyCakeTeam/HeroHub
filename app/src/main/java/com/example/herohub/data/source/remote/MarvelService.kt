package com.example.herohub.data.source.remote

import com.example.herohub.model.Character
import com.example.herohub.model.Story
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun getAllCharacters() : Single<Response<Character>>

    @GET("stories")
    fun getStoryDetails(@Query("id") id :String) :Single<Response<Story>>
}