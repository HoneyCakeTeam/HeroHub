package com.example.herohub.data

import com.example.herohub.data.source.remote.MarvelApi
import com.example.herohub.model.BaseResponse
import com.example.herohub.model.Character
import com.example.herohub.model.Comic
import com.example.herohub.model.Creator
import com.example.herohub.model.DataResponse
import com.example.herohub.model.Event
import com.example.herohub.model.Series
import com.example.herohub.model.Story
import com.example.herohub.utills.UiState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    private val api = MarvelApi.marvelService
    fun getAllCharacters(): Observable<UiState<DataResponse<Character>>> = wrapWithState {
        api.getAllCharacters()
    }

    fun getSeriesDetails(seriesId: Int): Observable<UiState<DataResponse<Series>>> = wrapWithState {
        api.getSeriesDetails(seriesId)
    }

    fun getEvent(eventId: Int): Observable<UiState<DataResponse<Event>>> = wrapWithState {
        api.getEvent(eventId)
    }

    fun getAllComics(): Observable<UiState<DataResponse<Comic>>> = wrapWithState {
        api.getAllComics()
    }

    fun getAllCreators(): Observable<UiState<DataResponse<Creator>>> = wrapWithState {
        api.getAllCreators()
    }

    fun getAllEvents(): Observable<UiState<DataResponse<Event>>> = wrapWithState {
        api.getAllEvents()
    }


    fun getAllStories(): Observable<UiState<DataResponse<Story>>> = wrapWithState {
        api.getAllStories()
    }

    fun getStoryDetails(id: String): Observable<UiState<DataResponse<Story>>> = wrapWithState {
        api.getStoryDetails(id)
    }


    fun getComic(comicId: Int): Observable<UiState<DataResponse<Comic>>> = wrapWithState {
        api.getComic(comicId)
    }

    fun getCharacterDetails(characterId: Int): Observable<UiState<DataResponse<Character>>> =
        wrapWithState { api.getCharacterDetails(characterId) }

    fun getCharacterComics(characterId: Int): Observable<UiState<DataResponse<Comic>>> =
        wrapWithState { api.getCharacterComics(characterId) }

    private fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>):
            Observable<UiState<DataResponse<T>>> {
        return function().toObservable().map {
            if (it.isSuccessful) {
                UiState.Success(it.body()?.data)
            } else {
                UiState.Error(it.message())
            }
        }.startWithItem(UiState.Loading)
    }


}

