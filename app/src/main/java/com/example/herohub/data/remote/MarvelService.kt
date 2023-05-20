package com.example.herohub.data.remote

import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.remote.model.CharacterDto
import com.example.herohub.data.remote.model.ComicDto
import com.example.herohub.data.remote.model.EventDto
import com.example.herohub.data.remote.model.SeriesDto
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
    ): Single<Response<BaseResponse<CharacterDto>>>

    @GET("events/{eventId}")
    fun getEvent(@Path("eventId") eventId: Int): Single<Response<BaseResponse<EventDto>>>

    @GET("characters")
    fun getAllCharacters(@Query("limit") limit: Int): Single<Response<BaseResponse<CharacterDto>>>

    @GET("characters/{characterId}")
    fun getCharacterDetails(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<CharacterDto>>>

    @GET("characters/{characterId}/events")
    fun getCharacterEvents(@Path("characterId") characterId: Int): Single<Response<BaseResponse<EventDto>>>

    @GET("characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") characterId: Int): Single<Response<BaseResponse<ComicDto>>>

    @GET("characters/{characterId}/series")
    fun getCharacterSeries(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<SeriesDto>>>


    @GET("comics/{comicId}")
    fun getComic(@Path("comicId") comicId: Int): Single<Response<BaseResponse<ComicDto>>>

    @GET("comics")
    fun getAllComics(@Query("limit") limit: Int): Single<Response<BaseResponse<ComicDto>>>

    @GET("events")
    fun getAllEvents(@Query("limit") limit: Int): Single<Response<BaseResponse<EventDto>>>

    @GET("series/{seriesId}")
    fun getSeriesDetails(@Path("seriesId") seriesId: Int): Single<Response<BaseResponse<SeriesDto>>>

    @GET("series")
    fun getAllSeries(@Query("limit") limit: Int): Single<Response<BaseResponse<SeriesDto>>>

}