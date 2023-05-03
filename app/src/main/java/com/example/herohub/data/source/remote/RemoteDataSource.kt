package com.example.herohub.data.source.remote

import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class RemoteDataSource {
    private val marvelApiService = MarvelApi.marvelService

    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()

    fun getCharacterDetails(characterId: Int): Single<Response<BaseResponse<Character>>> {
        return marvelApiService.getCharacterDetails(characterId)
    }
    fun getComicsForOneCharacter(characterId: Int): Single<Response<BaseResponse<Comic>>> {
        return marvelApiService.getComicsForOneCharacter(characterId)
    }
}