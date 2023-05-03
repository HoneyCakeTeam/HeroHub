package com.example.herohub.data.source.remote

class RemoteDataSource {
    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()
    fun getEvent(eventId: Int) = MarvelApi.marvelService.getEvent(eventId)
}