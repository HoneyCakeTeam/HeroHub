package com.example.herohub.data

import com.example.herohub.data.source.remote.MarvelApi
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    private val api = MarvelApi.marvelService
    fun getAllCharacters() = wrapWithState {
        api.getAllCharacters()
    }
    fun getAllSeries() = wrapWithState {
        api.getAllSeries()
    }

    fun getEvent(eventId: Int) = wrapWithState {
        api.getEvent(eventId)
    }

    fun getAllComics() = wrapWithState {
        api.getAllComics()
    }

    fun getAllCreators() = wrapWithState {
        api.getAllCreators()
    }

    fun getAllEvents() = wrapWithState {
        api.getAllEvents()
    }


    fun getAllStories() = wrapWithState {
        api.getAllStories()
    }

    fun getStoryDetails(id:String) = wrapWithState {
        api.getStoryDetails(id)
    }


    fun getComic(comicId: Int) = wrapWithState {
        api.getComic(comicId)
    }

    fun getCharacterDetails(characterId: Int) =
        wrapWithState { api.getCharacterDetails(characterId) }

    fun getCharacterComics(characterId: Int) =
        wrapWithState { api.getCharacterComics(characterId) }

    private fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<UiState<T>> {
        return function().map {
            try {
                if (it.isSuccessful) {
                    UiState.Success(it.body())
                } else {
                    UiState.Error(it.message())
                }
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Connection Failed")
            }
        }
    }
}