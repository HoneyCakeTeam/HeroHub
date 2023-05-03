package com.example.herohub.data.source.remote

import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Series
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface MarvelService {
    @GET("characters")
    fun getAllCharacters() : Single<Response<Character>>
    @GET("series")
    fun getAllSeries() : Single<Response<BaseResponse<Series>>>
}