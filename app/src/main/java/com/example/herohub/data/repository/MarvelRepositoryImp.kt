package com.example.herohub.data.repository

import com.example.herohub.data.remote.MarvelApi
import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.utils.SharedPreferencesUtils
import com.example.herohub.domain.mapper.CharacterMapper
import com.example.herohub.domain.mapper.ComicMapper
import com.example.herohub.domain.mapper.EventMapper
import com.example.herohub.domain.mapper.SeriesMapper
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImp {

    private val sharedPreferences = SharedPreferencesUtils
    private val characterMapper = CharacterMapper()
    private val comicMapper = ComicMapper()
    private val eventMapper = EventMapper()
    private val seriesMapper = SeriesMapper()
    private val api = MarvelApi.marvelService

    fun addToFavorite(favorite: FavoriteItem) {
        val gson = Gson()
        var stringFavorites = sharedPreferences.getItems()
        val favorites = convertToList(gson, stringFavorites)
        favorites?.let {
            it.add(favorite)
            stringFavorites = convertToString(gson, it)
        } ?: kotlin.run {
            stringFavorites = convertToString(gson, mutableListOf(favorite))
        }
        sharedPreferences.saveItems(stringFavorites)
    }

    fun removeFavorite(favorite: FavoriteItem) {
        val gson = Gson()
        var stringFavorites = sharedPreferences.getItems()
        val favorites = convertToList(gson, stringFavorites)
        favorites?.let {
            it.remove(favorite)
            stringFavorites = convertToString(gson, it)
            sharedPreferences.saveItems(stringFavorites)
        }
    }

    fun getFavorites(): List<FavoriteItem>? {
        val stringFavorites = sharedPreferences.getItems()
        val gson = Gson()
        return if (stringFavorites.isNullOrEmpty()) {
            emptyList()
        } else {
            convertToList(gson, stringFavorites)
        }
    }

    fun isFavorite(id: String): Boolean {
        val favorites = getFavorites()
        return favorites?.any { it.id == id } == true
    }

    private fun convertToString(
        gson: Gson,
        favorites: MutableList<FavoriteItem>?,
    ): String = gson.toJson(favorites)

    private fun convertToList(
        gson: Gson,
        stringFavorites: String?,
    ): MutableList<FavoriteItem>? = gson.fromJson(
        stringFavorites, object : TypeToken<List<FavoriteItem>>() {}.type
    )


    fun getCharactersByName(
        name: String,
    ): Single<UiState<List<Character>>> =
        wrapWithState({ api.getCharactersByName(name) }, { characterMapper.map(it) })

    fun getAllSeries(): Single<UiState<List<Series>>> =
        wrapWithState({ api.getAllSeries(100) }, { seriesMapper.map(it) })

    fun getSeriesDetails(seriesId: Int): Single<UiState<List<Series>>> =
        wrapWithState({ api.getSeriesDetails(seriesId) }, { seriesMapper.map(it) })

    fun getCharacterDetails(characterId: Int): Single<UiState<List<Character>>> =
        wrapWithState({ api.getCharacterDetails(characterId) }, { characterMapper.map(it) })

    fun getCharacterComics(characterId: Int): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getCharacterComics(characterId) }, { comicMapper.map(it) })

    fun getCharacterSeries(characterId: Int): Single<UiState<List<Series>>> =
        wrapWithState({ api.getCharacterSeries(characterId) }, { seriesMapper.map(it) })

    fun getCharacterEvents(characterId: Int): Single<UiState<List<Event>>> =
        wrapWithState({ api.getCharacterEvents(characterId) }, { eventMapper.map(it) })

    fun getEvent(eventId: Int): Single<UiState<List<Event>>> =
        wrapWithState({ api.getEvent(eventId) }, { eventMapper.map(it) })


    fun getAllComics(): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getAllComics(100) }, { comicMapper.map(it) })


    fun getAllEvents(): Single<UiState<List<Event>>> =
        wrapWithState({ api.getAllEvents(100) }, { eventMapper.map(it) })


    fun getComic(comicId: Int): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getComic(comicId) }, { comicMapper.map(it) })


    fun getAllCharacters(): Single<UiState<List<Character>>> =
        wrapWithState({ api.getAllCharacters(100) }, characterMapper::map)

    private fun <I, O> wrapWithState(
        function: () -> Single<Response<BaseResponse<I>>>,
        azizaMap: (List<I>) -> O,
    ): Single<UiState<O>> {
        return function().map {
            if (it.isSuccessful) {
                UiState.Success(azizaMap(it.body()?.data?.results ?: emptyList()))
            } else {
                UiState.Error(it.message())
            }
        }
    }
}

