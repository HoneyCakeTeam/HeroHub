package com.example.herohub.data.source.remote

class RemoteDataSource {
    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()
    fun getAllSeries() = MarvelApi.marvelService.getAllSeries()
}