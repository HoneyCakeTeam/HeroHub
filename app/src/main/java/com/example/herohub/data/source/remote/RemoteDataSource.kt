package com.example.herohub.data.source.remote

class RemoteDataSource {
    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()
    fun getStoryDetails(id:String) = MarvelApi.marvelService.getStoryDetails(id)
}