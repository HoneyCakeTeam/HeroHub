package com.example.herohub.data.source.remote

import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.model.Comic
import com.example.herohub.model.Creator
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.model.Story
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {
    @GET("events/{eventId}")
    fun getEvent(@Path("eventId") eventId: Int): Single<Response<BaseResponse<Event>>>

    @GET("characters")
    fun getAllCharacters(): Single<Response<Character>>

    @GET("comics")
    fun getAllComics(): Single<Response<Comic>>

    @GET("creators")
    fun getAllCreators(): Single<Response<Creator>>

    @GET("events")
    fun getAllEvents(): Single<Response<Event>>

    @GET("series")
    fun getAllSeries(): Single<Response<Series>>

    @GET("stories")
    fun getAllStories(): Single<Response<Story>>
}