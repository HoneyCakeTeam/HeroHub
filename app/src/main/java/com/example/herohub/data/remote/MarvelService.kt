package com.example.herohub.data.remote

import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.remote.model.CharacterDto
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.Series
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelService {

    @GET("characters")
    fun getCharactersByName(
        @Query("nameStartsWith")
        name: String,
    ): Single<Response<BaseResponse<Character>>>

    @GET("events/{eventId}")
    fun getEvent(@Path("eventId") eventId: Int): Single<Response<BaseResponse<Event>>>

    @GET("characters")
    fun getAllCharacters(@Query("limit") limit: Int): Single<Response<BaseResponse<CharacterDto>>>

    @GET("characters/{characterId}")
    fun getCharacterDetails(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<Character>>>

    @GET("characters/{characterId}/events")
    fun getCharacterEvents(@Path("characterId") characterId: Int): Single<Response<BaseResponse<Event>>>

    @GET("characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: Int): Single<Response<BaseResponse<Comic>>>

    @GET("characters/{characterId}/series")
    fun getCharacterSeries(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<Series>>>


    @GET("comics/{comicId}")
    fun getComic(@Path("comicId") comicId: Int): Single<Response<BaseResponse<Comic>>>

    @GET("comics")
    fun getAllComics(@Query("limit") limit: Int): Single<Response<BaseResponse<Comic>>>

    @GET("events")
    fun getAllEvents(@Query("limit") limit: Int): Single<Response<BaseResponse<Event>>>

    @GET("series/{seriesId}")
    fun getSeriesDetails(@Path("seriesId") seriesId: Int): Single<Response<BaseResponse<Series>>>

    @GET("series")
    fun getAllSeries(@Query("limit") limit: Int): Single<Response<BaseResponse<Series>>>

}