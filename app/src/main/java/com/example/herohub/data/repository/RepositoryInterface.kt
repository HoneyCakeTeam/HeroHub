package com.example.herohub.data.repository

import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.domain.model.*
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface RepositoryInterface {

    fun addToFavorite(favorite: FavoriteItem)

    fun removeFavorite(favorite: FavoriteItem)

    fun getFavorites(): List<FavoriteItem>?

    fun isFavorite(id: String): Boolean

    fun convertToString(
        gson: Gson,
        favorites: MutableList<FavoriteItem>?,
    ): String

    fun convertToList(
        gson: Gson,
        stringFavorites: String?,
    ): MutableList<FavoriteItem>?

    fun getAllCharacters(): Single<UiState<DataResponse<Character>>>

    fun getCharactersByName(
        name: String,
    ): Single<UiState<DataResponse<Character>>>

    fun getAllSeries(): Single<UiState<DataResponse<Series>>>

    fun getSeriesDetails(seriesId: Int): Single<UiState<DataResponse<Series>>>

    fun getCharacterDetails(characterId: Int): Single<UiState<DataResponse<Character>>>

    fun getCharacterComics(characterId: Int): Single<UiState<DataResponse<Comic>>>

    fun getCharacterSeries(characterId: Int): Single<UiState<DataResponse<Series>>>

    fun getCharacterEvents(characterId: Int): Single<UiState<DataResponse<Event>>>

    fun getEvent(eventId: Int): Single<UiState<DataResponse<Event>>>

    fun getAllComics(): Single<UiState<DataResponse<Comic>>>

    fun getAllEvents(): Single<UiState<DataResponse<Event>>>

    fun getComic(comicId: Int): Single<UiState<DataResponse<Comic>>>

    fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>):
            Single<UiState<DataResponse<T>>>
}