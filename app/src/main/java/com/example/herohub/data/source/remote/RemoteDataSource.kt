package com.example.herohub.data.source.remote

class RemoteDataSource {
    private val marvelService = MarvelApi.marvelService
    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()
    fun getCharacterDetails(characterId: Int) = marvelService.getCharacterDetails(characterId)
}