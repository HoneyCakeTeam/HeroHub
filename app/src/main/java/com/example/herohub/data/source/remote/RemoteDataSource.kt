package com.example.herohub.data.source.remote

class RemoteDataSource {
    private val marvelService = MarvelApi.marvelService
    fun getAllCharacters() = marvelService.getAllCharacters()
    fun getAllComics() = MarvelApi.marvelService.getAllComics()
    fun getAllCreators() = MarvelApi.marvelService.getAllCreators()
    fun getAllEvents() = MarvelApi.marvelService.getAllEvents()
    fun getAllSeries() = MarvelApi.marvelService.getAllSeries()
    fun getAllStories() = MarvelApi.marvelService.getAllStories()
    fun getEvent(eventId: Int) = MarvelApi.marvelService.getEvent(eventId)
    fun getStoryDetails(id: String) = MarvelApi.marvelService.getStoryDetails(id)

    fun getComic(comicId: Int) = MarvelApi.marvelService.getComic(comicId)

    fun getCharacterDetails(characterId: Int) = marvelService.getCharacterDetails(characterId)
    fun getCharacterComics(characterId: Int) = marvelService.getCharacterComics(characterId)
}