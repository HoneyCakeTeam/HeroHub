package com.example.herohub.data.repository

import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
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

    fun saveSearchKeyword(keyword: String): Completable

    //endregion
    //region remote db
    fun refreshCharacters()
    fun refreshComics()
    fun refreshEvents()
    fun refreshSeries()
    fun refreshSlider()
    fun getAllCharacters(): Single<UiState<List<Character>>>// 4 see all

    fun getAllCharactersFromDB(): Observable<List<Character>>

    fun getEventDetails(eventId: Int): Single<UiState<List<Event>>>
    fun getAllSeries(): Single<UiState<List<Series>>>// 4 see all
    fun getAllSeriesFromDB(): Observable<List<Series>>
    fun getAllComics(): Single<UiState<List<Comic>>>// 4 see all
    fun getAllComicsFromDB(): Observable<List<Comic>>
    fun getAllEvents(): Single<UiState<List<Event>>>// 4 see all
    fun getAllEventsFromDB(): Observable<List<Event>>
    fun getComicDetails(comicId: Int): Single<UiState<List<Comic>>>
    fun getSeriesDetails(seriesId: Int): Single<UiState<List<Series>>>

    fun getCharacterDetails(characterId: Int): Single<UiState<List<Character>>>

    fun getCharacterComics(characterId: Int): Single<UiState<List<Comic>>>

    fun getCharacterSeries(characterId: Int): Single<UiState<List<Series>>>

    fun getCharacterEvents(characterId: Int): Single<UiState<List<Event>>>

    fun getCharactersByName(
        name: String,
    ): Single<UiState<List<Character>>>


    //endregion
}