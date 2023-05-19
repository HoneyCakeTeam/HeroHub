package com.example.herohub.data.repository

import com.example.herohub.data.local.SearchHistoryEntity
import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.utils.SharedPreferencesUtils
import com.example.herohub.domain.mapper.MapperContainer
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.SearchHistory
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val mapperContainer: MapperContainer,
    private val dao: MarvelDao,
    private val api: MarvelService,
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

    override fun saveCharacterNameLocal(name: String, id: Long): Completable {
        return dao.insertCharacterName(
            SearchHistoryEntity(id = id, name = name)
        )
    }

    override fun getSearchHistory(): Single<List<SearchHistory>> {
        return dao.getSearchHistory().map { mapperContainer.searchHistoryMapper.map(it) }
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
            { mapperContainer.characterMapper.map(it) })

    override fun getAllSeries(): Single<UiState<List<Series>>> =
        wrapWithState({ api.getAllSeries(100) }, { mapperContainer.seriesMapper.map(it) })

    override fun getSeriesDetails(seriesId: Int): Single<UiState<List<Series>>> =
        wrapWithState({ api.getSeriesDetails(seriesId) }, { mapperContainer.seriesMapper.map(it) })

    override fun getCharacterDetails(characterId: Int): Single<UiState<List<Character>>> =
        wrapWithState(
            { api.getCharacterDetails(characterId) },
            { mapperContainer.characterMapper.map(it) })

    override fun getCharacterComics(characterId: Int): Single<UiState<List<Comic>>> =
        wrapWithState(
            { api.getCharacterComics(characterId) },
            { mapperContainer.comicMapper.map(it) })

    override fun getCharacterSeries(characterId: Int): Single<UiState<List<Series>>> =
        wrapWithState(
            { api.getCharacterSeries(characterId) },
            { mapperContainer.seriesMapper.map(it) })

    override fun getCharacterEvents(characterId: Int): Single<UiState<List<Event>>> =
        wrapWithState(
            { api.getCharacterEvents(characterId) },
            { mapperContainer.eventMapper.map(it) })

    override fun getEvent(eventId: Int): Single<UiState<List<Event>>> =
        wrapWithState({ api.getEvent(eventId) }, { mapperContainer.eventMapper.map(it) })


    override fun getAllComics(): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getAllComics(100) }, { mapperContainer.comicMapper.map(it) })


    override fun getAllEvents(): Single<UiState<List<Event>>> =
        wrapWithState({ api.getAllEvents(100) }, { mapperContainer.eventMapper.map(it) })


    override fun getComic(comicId: Int): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getComic(comicId) }, { mapperContainer.comicMapper.map(it) })


    override fun getAllCharacters(): Single<UiState<List<Character>>> =
        wrapWithState({ api.getAllCharacters(100) }, mapperContainer.characterMapper::map)

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

