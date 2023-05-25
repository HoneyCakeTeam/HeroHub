package com.example.herohub.data.repository

import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow

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

    suspend fun saveSearchKeyword(keyword: String)

    //endregion
    //region remote db
    suspend fun refreshCharacters()
    suspend fun refreshComics()
    suspend fun refreshEvents()
    suspend fun refreshSeries()
    suspend fun refreshSlider()

    fun getAllCharacters(): Flow<UiState<List<Character>>>// 4 see all
    fun getAllCharactersFromDB(): Flow<List<Character>>
    fun getAllSeries(): Flow<UiState<List<Series>>>// 4 see all
    fun getAllSeriesFromDB(): Flow<List<Series>>
    fun getAllComics(): Flow<UiState<List<Comic>>> // 4 see all
    fun getAllComicsFromDB(): Flow<List<Comic>>
    fun getAllEvents(): Flow<UiState<List<Event>>> // 4 see all
    fun getAllEventsFromDB(): Flow<List<Event>>

    fun getEventDetails(eventId: Int): Flow<UiState<List<Event>>>
    fun getComicDetails(comicId: Int): Flow<UiState<List<Comic>>>
    fun getSeriesDetails(seriesId: Int): Flow<UiState<List<Series>>>
    fun getCharacterDetails(characterId: Int): Flow<UiState<List<Character>>>

    fun getCharacterComics(characterId: Int): Flow<UiState<List<Comic>>>
    fun getCharacterSeries(characterId: Int): Flow<UiState<List<Series>>>
    fun getCharacterEvents(characterId: Int): Flow<UiState<List<Event>>>

    fun getCharactersByName(
        name: String,
    ): Flow<UiState<List<Character>>>


    //endregion

}