package com.example.herohub.data.source.remote

import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {
    @GET("characters")
    fun getAllCharacters() : Single<Response<Character>>

    @GET("comics/{comicId}")
    fun getComic(@Path("comicId") comicId : Int ) : Single<Response<BaseResponse<Comic>>>
}