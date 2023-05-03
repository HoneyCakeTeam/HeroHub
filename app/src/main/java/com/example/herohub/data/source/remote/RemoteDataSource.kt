package com.example.herohub.data.source.remote

class RemoteDataSource {
    fun getAllCharacters() = MarvelApi.marvelService.getAllCharacters()

    fun getComic(comicId: Int) = MarvelApi.marvelService.getComic(comicId)

}