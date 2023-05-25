package com.example.herohub.data.repository

import com.example.herohub.data.local.SearchHistoryEntity
import com.example.herohub.data.local.dao.MarvelDao
import com.example.herohub.data.local.dto_to_entity_mapper.DtoToEntityContainer
import com.example.herohub.data.remote.MarvelService
import com.example.herohub.data.remote.model.BaseResponse
import com.example.herohub.data.utils.SharedPreferencesUtils
import com.example.herohub.domain.mapper.dto_to_domain_mapper.DtoToDomainContainer
import com.example.herohub.domain.mapper.entity_to_domain_mapper.EntityToDomainContainer
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.FavoriteItem
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.utils.UiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val dtoToEntityContainer: DtoToEntityContainer,
    private val entityToDomainContainer: EntityToDomainContainer,
    private val dtoToDomainContainer: DtoToDomainContainer,
    private val api: MarvelService,
    private val dao: MarvelDao,
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
        gson: Gson, favorites: MutableList<FavoriteItem>?,
    ): String = gson.toJson(favorites)

    override fun convertToList(
        gson: Gson, stringFavorites: String?,
    ): MutableList<FavoriteItem>? = gson.fromJson(
        stringFavorites, object : TypeToken<List<FavoriteItem>>() {}.type
    )

    override suspend fun saveSearchKeyword(keyword: String) {
        return dao.insertSearchKeyword(SearchHistoryEntity(name = keyword))
    }


    //region call from api

    override fun getCharactersByName(name: String): Flow<UiState<List<Character>>> =
        wrapWithState({ api.getCharactersByName(name) },
            { dtoToDomainContainer.characterDtoToCharacter.map(it) })

    override fun getCharacterDetails(characterId: Int): Flow<UiState<List<Character>>> =
        wrapWithState(
            { api.getCharacterDetails(characterId) },
            { dtoToDomainContainer.characterDtoToCharacter.map(it) })

    override fun getEventDetails(eventId: Int): Flow<UiState<List<Event>>> =
        wrapWithState({ api.getEvent(eventId) },
            { dtoToDomainContainer.eventDtoToEvent.map(it) })


    override fun getComicDetails(comicId: Int): Flow<UiState<List<Comic>>> =
        wrapWithState({ api.getComic(comicId) },
            { dtoToDomainContainer.comicDtoToComic.map(it) })

    override fun getSeriesDetails(seriesId: Int): Flow<UiState<List<Series>>> =
        wrapWithState(
            { api.getSeriesDetails(seriesId) },
            { dtoToDomainContainer.seriesDtoToSeries.map(it) })

    override fun getCharacterComics(characterId: Int): Flow<UiState<List<Comic>>> =
        wrapWithState(
            { api.getCharacterComics(characterId) },
            { dtoToDomainContainer.comicDtoToComic.map(it) })

    override fun getCharacterSeries(characterId: Int): Flow<UiState<List<Series>>> =
        wrapWithState({ api.getCharacterSeries(characterId) },
            { dtoToDomainContainer.seriesDtoToSeries.map(it) })

    override fun getCharacterEvents(characterId: Int): Flow<UiState<List<Event>>> = wrapWithState(
        { api.getCharacterEvents(characterId) },
        { dtoToDomainContainer.eventDtoToEvent.map(it) })

    //endregion

    //region see all ( from API)
    override fun getAllCharacters(): Flow<UiState<List<Character>>> =
        wrapWithState({ api.getAllCharacters(100) },
            { dtoToDomainContainer.characterDtoToCharacter.map(it) })

    override fun getAllSeries(): Flow<UiState<List<Series>>> {
        return wrapWithState({ api.getAllSeries(100) },
            { dtoToDomainContainer.seriesDtoToSeries.map(it) })
    }

    override fun getAllEvents(): Flow<UiState<List<Event>>> {
        return wrapWithState({ api.getAllEvents(100) },
            { dtoToDomainContainer.eventDtoToEvent.map(it) })
    }


    override fun getAllComics(): Flow<UiState<List<Comic>>> {
        return wrapWithState({ api.getAllComics(100) },
            { dtoToDomainContainer.comicDtoToComic.map(it) })
    }

    //endregion


    // region refresh data
    override suspend fun refreshCharacters() {
        val result = api.getAllCharacters(100).body()?.data?.results
        result?.let {
            val characterEntities = dtoToEntityContainer.characterMapper.map(it)
            dao.insertAllCharacters(characterEntities)
        }
    }

    override suspend fun refreshComics() {
        val result = api.getAllComics(100).body()?.data?.results
        result?.let {
            val comicEntities = dtoToEntityContainer.comicMapper.map(it)
            dao.insertAllComics(comicEntities)
        }
    }

    override suspend fun refreshEvents() {
        val result = api.getAllEvents(100).body()?.data?.results
        result?.let {
            val eventEntities = dtoToEntityContainer.eventMapper.map(it)
            dao.insertAllEvents(eventEntities)
        }
    }

    override suspend fun refreshSeries() {
        val result = api.getAllSeries(100).body()?.data?.results
        result?.let {
            val seriesEntities = dtoToEntityContainer.seriesMapper.map(it)
            dao.insertAllSeries(seriesEntities)
        }
    }

    override suspend fun refreshSlider() {
        val result = api.getAllEvents(100).body()?.data?.results
        result?.let {
            val eventEntities = dtoToEntityContainer.eventMapper.map(it)
            dao.insertAllEvents(eventEntities)
        }
    }


//endregion

    //region cashing (get from Room DB)
    override fun getAllCharactersFromDB(): Flow<List<Character>> =
        dao.getAllCharacters().map { entityToDomainContainer.characterMapper.map(it) }

    override fun getAllSeriesFromDB(): Flow<List<Series>> =
        dao.getAllSeries().map { entityToDomainContainer.seriesMapper.map(it) }

    override fun getAllComicsFromDB(): Flow<List<Comic>> =
        dao.getAllComics().map { entityToDomainContainer.comicMapper.map(it) }


    override fun getAllEventsFromDB(): Flow<List<Event>> =
        dao.getAllEvents().map { entityToDomainContainer.eventMapper.map(it) }


    //endregion


    private fun <I, O> wrapWithState(
        response: suspend () -> Response<BaseResponse<I>>, map: (List<I>) -> O,
    ): Flow<UiState<O>> {
        return flow {
            emit(UiState.Loading)
            try {
                if (response().isSuccessful) {
                    emit(UiState.Success(map(response().body()?.data?.results ?: emptyList())))
                } else {
                    emit(UiState.Error(response().message()))
                }
            } catch (e: Exception) {
                emit(UiState.Error(e.message ?: "Unknown error occurred"))
            }
        }
    }

}

