package com.example.herohub.data.source.remote

import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.Creator
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.model.Story
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelService {
    @GET("events/{eventId}")
    fun getEvent(@Path("eventId") eventId: Int): Single<Response<BaseResponse<Event>>>

    @GET("characters")
    fun getAllCharacters(@Query("limit") limit: Int): Single<Response<BaseResponse<Character>>>

    @GET("characters/{characterId}")
    fun getCharacterDetails(
        @Path("characterId") characterId: Int
    ): Single<Response<BaseResponse<Character>>>

    @GET("characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: Int): Single<Response<BaseResponse<Comic>>>

    @GET("comics/{comicId}")
    fun getComic(@Path("comicId") comicId: Int): Single<Response<BaseResponse<Comic>>>

    @GET("comics")
    fun getAllComics(@Query("limit") limit: Int): Single<Response<BaseResponse<Comic>>>

    @GET("creators")
    fun getAllCreators(): Single<Response<BaseResponse<Creator>>>

    @GET("events")
    fun getAllEvents(@Query("limit") limit: Int): Single<Response<BaseResponse<Event>>>

    @GET("series")
    fun getAllSeries(@Query("limit") limit: Int): Single<Response<BaseResponse<Series>>>

    @GET("stories")
    fun getAllStories(): Single<Response<BaseResponse<Story>>>

    @GET("stories")
    fun getStoryDetails(@Query("id") id: String): Single<Response<BaseResponse<Story>>>
}