package com.example.herohub.data.source.remote

class RemoteDataSource {
    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()
    fun getAllComics() = MarvelApi.marvelService.getAllComics()
    fun getAllCreators() = MarvelApi.marvelService.getAllCreators()
    fun getAllEvents() = MarvelApi.marvelService.getAllEvents()
    fun getAllSeries() = MarvelApi.marvelService.getAllSeries()
    fun getAllStories() = MarvelApi.marvelService.getAllStories()
    fun getEvent(eventId: Int) = MarvelApi.marvelService.getEvent(eventId)
    fun getStoryDetails(id:String) = MarvelApi.marvelService.getStoryDetails(id)
}