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
    fun getAllCharacters(): Observable<UiState<List<Character>>>// 4 see all

    fun getAllCharactersFromDB(): Observable<List<Character>>

    fun getEventDetails(eventId: Int): Observable<UiState<List<Event>>>
    fun getAllSeries(): Observable<UiState<List<Series>>>// 4 see all
    fun getAllSeriesFromDB(): Observable<List<Series>>
    fun getAllComics(): Observable<UiState<List<Comic>>>// 4 see all
    fun getAllComicsFromDB(): Observable<List<Comic>>
    fun getAllEvents(): Observable<UiState<List<Event>>>// 4 see all
    fun getAllEventsFromDB(): Observable<List<Event>>
    fun getComicDetails(comicId: Int): Observable<UiState<List<Comic>>>
    fun getSeriesDetails(seriesId: Int): Observable<UiState<List<Series>>>

    fun getCharacterDetails(characterId: Int): Observable<UiState<List<Character>>>

    fun getCharacterComics(characterId: Int): Observable<UiState<List<Comic>>>

    fun getCharacterSeries(characterId: Int): Observable<UiState<List<Series>>>

    fun getCharacterEvents(characterId: Int): Observable<UiState<List<Event>>>

    fun getCharactersByName(
        name: String,
    ): Observable<UiState<List<Character>>>


    //endregion
}