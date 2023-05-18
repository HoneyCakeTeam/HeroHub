package com.example.herohub.data.repository

import com.example.herohub.data.remote.MarvelApi
import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.remote.model.DataResponse
import com.example.herohub.data.remote.model.StoryDto
import com.example.herohub.data.utils.SharedPreferencesUtils
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

class MarvelRepository : RepositoryInterface{

    private val sharedPreferences = SharedPreferencesUtils
    private val api = MarvelApi.marvelService

    override fun addToFavorite(favorite: FavoriteItem) {
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

    override fun removeFavorite(favorite: FavoriteItem) {
        val gson = Gson()
        var stringFavorites = sharedPreferences.getItems()
        val favorites = convertToList(gson, stringFavorites)
        favorites?.let {
            it.remove(favorite)
            stringFavorites = convertToString(gson, it)
            sharedPreferences.saveItems(stringFavorites)
        }
    }

    override fun getFavorites(): List<FavoriteItem>? {
        val stringFavorites = sharedPreferences.getItems()
        val gson = Gson()
        return if (stringFavorites.isNullOrEmpty()) {
            emptyList()
        } else {
            convertToList(gson, stringFavorites)
        }
    }

    override fun isFavorite(id: String): Boolean {
        val favorites = getFavorites()
        return favorites?.any { it.id == id } == true
    }

    override fun convertToString(
        gson: Gson,
        favorites: MutableList<FavoriteItem>?,
    ): String = gson.toJson(favorites)

    override fun convertToList(
        gson: Gson,
        stringFavorites: String?,
    ): MutableList<FavoriteItem>? = gson.fromJson(
        stringFavorites, object : TypeToken<List<FavoriteItem>>() {}.type
    )

    override fun getAllCharacters(): Single<UiState<DataResponse<Character>>> = wrapWithState {
        api.getAllCharacters(100)
    }

    override fun getCharactersByName(
        name: String,
    ): Single<UiState<DataResponse<Character>>> = wrapWithState {
        api.getCharactersByName(name)
    }


    override fun getAllSeries(): Single<UiState<DataResponse<Series>>> = wrapWithState {
        api.getAllSeries(100)
    }

    override fun getSeriesDetails(seriesId: Int): Single<UiState<DataResponse<Series>>> = wrapWithState {
        api.getSeriesDetails(seriesId)
    }

    override fun getCharacterDetails(characterId: Int): Single<UiState<DataResponse<Character>>> =
        wrapWithState { api.getCharacterDetails(characterId) }

    override fun getCharacterComics(characterId: Int): Single<UiState<DataResponse<Comic>>> =
        wrapWithState { api.getCharacterComics(characterId) }

    override fun getCharacterSeries(characterId: Int): Single<UiState<DataResponse<Series>>> =
        wrapWithState { api.getCharacterSeries(characterId) }

    override fun getCharacterEvents(characterId: Int): Single<UiState<DataResponse<Event>>> =
        wrapWithState { api.getCharacterEvents(characterId) }

    override fun getEvent(eventId: Int): Single<UiState<DataResponse<Event>>> = wrapWithState {
        api.getEvent(eventId)
    }

    override fun getAllComics(): Single<UiState<DataResponse<Comic>>> = wrapWithState {
        api.getAllComics(100)
    }

    override fun getAllEvents(): Single<UiState<DataResponse<Event>>> = wrapWithState {
        api.getAllEvents(100)
    }

    override fun getComic(comicId: Int): Single<UiState<DataResponse<Comic>>> = wrapWithState {
        api.getComic(comicId)
    }


    override fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>):
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

