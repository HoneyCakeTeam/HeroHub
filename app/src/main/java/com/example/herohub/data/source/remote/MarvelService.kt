package com.example.herohub.data.source.remote

import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    fun getAllCharacters() : Single<Response<Character>>

    @GET("characters/{characterId}")
    fun getCharacterDetails(
        @Query("characterId") characterId: Int,
    ): Single<Response<BaseResponse<Character>>>

    @GET("characters/{characterId}/comics")
    fun getComicsForOneCharacter(
        @Query("characterId") characterId: Int,
    ): Single<Response<BaseResponse<Comic>>>

}