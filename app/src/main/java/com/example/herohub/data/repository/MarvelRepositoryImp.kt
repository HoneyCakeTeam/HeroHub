package com.example.herohub.data.repository

import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.local.dto_to_entity_mapper.MapperEntityContainer
import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.utils.SharedPreferencesUtils
import com.example.herohub.domain.entity_to_domain_mapper.MapperUiContainer
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val mapperEntityContainer: MapperEntityContainer,
    private val mapperUiContainer: MapperUiContainer,
    private val api: MarvelService,
    private val dao: MarvelDao
) : MarvelRepository {

    private val sharedPreferences = SharedPreferencesUtils
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


    override fun getCharactersByName(
        name: String,
    ): Single<UiState<List<Character>>> =
        wrapWithState(
            { api.getCharactersByName(name) },
            { mapperEntityContainer.characterMapper.map(it) })

    override fun getAllSeries(): Single<UiState<List<Series>>> =
        wrapWithState({ api.getAllSeries(100) }, { mapperEntityContainer.seriesMapper.map(it) })

    override fun getSeriesDetails(seriesId: Int): Single<UiState<List<Series>>> =
        wrapWithState(
            { api.getSeriesDetails(seriesId) },
            { mapperEntityContainer.seriesMapper.map(it) })

    override fun getCharacterDetails(characterId: Int): Single<UiState<List<Character>>> =
        wrapWithState(
            { api.getCharacterDetails(characterId) },
            { mapperEntityContainer.characterMapper.map(it) })

    override fun getCharacterComics(characterId: Int): Single<UiState<List<Comic>>> =
        wrapWithState(
            { api.getCharacterComics(characterId) },
            { mapperEntityContainer.comicMapper.map(it) })

    override fun getCharacterSeries(characterId: Int): Single<UiState<List<Series>>> =
        wrapWithState(
            { api.getCharacterSeries(characterId) },
            { mapperEntityContainer.seriesMapper.map(it) })

    override fun getCharacterEvents(characterId: Int): Single<UiState<List<Event>>> =
        wrapWithState(
            { api.getCharacterEvents(characterId) },
            { mapperEntityContainer.eventMapper.map(it) })

    override fun getEvent(eventId: Int): Single<UiState<List<Event>>> =
        wrapWithState({ api.getEvent(eventId) }, { mapperEntityContainer.eventMapper.map(it) })


    override fun getAllComics(): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getAllComics(100) }, { mapperEntityContainer.comicMapper.map(it) })


    override fun getAllEvents(): Single<UiState<List<Event>>> =
        wrapWithState({ api.getAllEvents(100) }, { mapperEntityContainer.eventMapper.map(it) })


    override fun getComic(comicId: Int): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getComic(comicId) }, { mapperEntityContainer.comicMapper.map(it) })


    override fun getAllCharacters(): Observable<List<Character>> =
        dao.getAllCharacters().map { mapperEntityContainer.characterMapper.map { it } }

    override fun refreshCharacters() {
        wrapWithState({ api.getAllCharacters(100) }, mapperEntityContainer.characterMapper::map)
        dao.insertAllCharacters()
    }

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

