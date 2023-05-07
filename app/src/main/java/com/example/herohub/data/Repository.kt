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
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class Repository {
    private val api = MarvelApi.marvelService
    fun getAllCharacters(): Single<UiState<DataResponse<Character>>> = wrapWithState {
        api.getAllCharacters(100)
    }

    fun getAllSeries(): Single<UiState<DataResponse<Series>>> = wrapWithState {
        api.getAllSeries(100)
    }

    fun getSeriesDetails(seriesId: Int): Single<UiState<DataResponse<Series>>> = wrapWithState {
        api.getSeriesDetails(seriesId)
    }

    fun getEvent(eventId: Int): Single<UiState<DataResponse<Event>>> = wrapWithState {
        api.getEvent(eventId)
    }

    fun getAllComics(): Single<UiState<DataResponse<Comic>>> = wrapWithState {
        api.getAllComics(100)
    }

    fun getAllCreators(): Single<UiState<DataResponse<Creator>>> = wrapWithState {
        api.getAllCreators()
    }

    fun getAllEvents(): Single<UiState<DataResponse<Event>>> = wrapWithState {
        api.getAllEvents(100)
    }


    fun getAllStories(): Single<UiState<DataResponse<Story>>> = wrapWithState {
        api.getAllStories()
    }

    fun getStoryDetails(id: String): Single<UiState<DataResponse<Story>>> = wrapWithState {
        api.getStoryDetails(id)
    }


    fun getComic(comicId: Int): Single<UiState<DataResponse<Comic>>> = wrapWithState {
        api.getComic(comicId)
    }

    fun getCharacterDetails(characterId: Int): Single<UiState<DataResponse<Character>>> =
        wrapWithState { api.getCharacterDetails(characterId) }

    fun getCharacterComics(characterId: Int): Single<UiState<DataResponse<Comic>>> =
        wrapWithState { api.getCharacterComics(characterId) }

    private fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>):
            Single<UiState<DataResponse<T>>> {
        return function().map {
            if (it.isSuccessful) {
                UiState.Success(it.body()?.data)
            } else {
                UiState.Error(it.message())
            }
        }
    }


}

