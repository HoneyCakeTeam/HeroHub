package com.example.herohub.data.repository

import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    //region local db
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

    //endregion

    //region remote db
    fun getAllCharacters(): Single<UiState<List<Character>>>

    fun getCharactersByName(
        name: String,
    ): Single<UiState<List<Character>>>

    fun getAllSeries(): Single<UiState<List<Series>>>

    fun getSeriesDetails(seriesId: Int): Single<UiState<List<Series>>>

    fun getCharacterDetails(characterId: Int): Single<UiState<List<Character>>>

    fun getCharacterComics(characterId: Int): Single<UiState<List<Comic>>>

    fun getCharacterSeries(characterId: Int): Single<UiState<List<Series>>>

    fun getCharacterEvents(characterId: Int): Single<UiState<List<Event>>>

    fun getEvent(eventId: Int): Single<UiState<List<Event>>>

    fun getAllComics(): Single<UiState<List<Comic>>>

    fun getAllEvents(): Single<UiState<List<Event>>>

    fun getComic(comicId: Int): Single<UiState<List<Comic>>>

    //endregion
}