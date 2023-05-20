package com.example.herohub.data.repository

import android.annotation.SuppressLint
import android.util.Log
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
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
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
            { dtoToDomainContainer.characterDtoToCharacter.map(it) })


    //region call from api
    override fun getCharacterDetails(characterId: Int): Single<UiState<List<Character>>> =
        wrapWithState(
            { api.getCharacterDetails(characterId) },
            { dtoToDomainContainer.characterDtoToCharacter.map(it) })

    override fun getEventDetails(eventId: Int): Single<UiState<List<Event>>> =
        wrapWithState({ api.getEvent(eventId) },
            { dtoToDomainContainer.eventDtoToEvent.map(it) })


    override fun getComicDetails(comicId: Int): Single<UiState<List<Comic>>> =
        wrapWithState({ api.getComic(comicId) },
            { dtoToDomainContainer.comicDtoToComic.map(it) })

    override fun getSeriesDetails(seriesId: Int): Single<UiState<List<Series>>> =
        wrapWithState(
            { api.getSeriesDetails(seriesId) },
            { dtoToDomainContainer.seriesDtoToSeries.map(it) })

    override fun getCharacterComics(characterId: Int): Single<UiState<List<Comic>>> =
        wrapWithState(
            { api.getCharacterComics(characterId) },
            { dtoToDomainContainer.comicDtoToComic.map(it) })

    override fun getCharacterSeries(characterId: Int): Single<UiState<List<Series>>> =
        wrapWithState({ api.getCharacterSeries(characterId) },
            { dtoToDomainContainer.seriesDtoToSeries.map(it) })

    override fun getCharacterEvents(characterId: Int): Single<UiState<List<Event>>> = wrapWithState(
        { api.getCharacterEvents(characterId) },
        { dtoToDomainContainer.eventDtoToEvent.map(it) })

    //endregion

    //region see all ( from API)
    override fun getAllCharacters(): Single<UiState<List<Character>>> =
        wrapWithState({ api.getAllCharacters(100) },
            { dtoToDomainContainer.characterDtoToCharacter.map(it) })

    override fun getAllSeries(): Single<UiState<List<Series>>> {
        return wrapWithState({ api.getAllSeries(100) },
            { dtoToDomainContainer.seriesDtoToSeries.map(it) })
    }

    override fun getAllComics(): Single<UiState<List<Comic>>> {
        return wrapWithState({ api.getAllComics(100) },
            { dtoToDomainContainer.comicDtoToComic.map(it) })
    }

    override fun getAllEvents(): Single<UiState<List<Event>>> {
        return wrapWithState({ api.getAllEvents(100) },
            { dtoToDomainContainer.eventDtoToEvent.map(it) })
    }

    //endregion

    //region refresh
    @SuppressLint("CheckResult")
    override fun refreshCharacters() {
        wrapWithState(
            { api.getAllCharacters(100) }, dtoToEntityContainer.characterMapper::map
        ).subscribe(
            {
                it.toData()?.let { characterEntities ->
                    dao.insertAllCharacters(characterEntities).subscribeOn(Schedulers.io())
                        .subscribe()
                }
            }, {
                Log.e("Repository", "Repository")
            }
        )
    }

    @SuppressLint("CheckResult")
    override fun refreshComics() {
        wrapWithState(
            { api.getAllComics(100) }, dtoToEntityContainer.comicMapper::map
        ).subscribe(
            {
                it.toData()?.let { comicEntity ->
                    dao.insertAllComics(comicEntity).subscribeOn(Schedulers.io()).subscribe()
                }
            }, {
                Log.e("Repository", "Repository")
            }
        )
    }

    @SuppressLint("CheckResult")
    override fun refreshEvents() {
        wrapWithState(
            { api.getAllEvents(100) }, dtoToEntityContainer.eventMapper::map
        ).subscribe(
            {
                it.toData()?.let { eventEntity ->
                    dao.insertAllEvents(eventEntity).subscribeOn(Schedulers.io()).subscribe()
                }
            }, {
                Log.e("Repository", "Repository")
            }
        )
    }

    @SuppressLint("CheckResult")
    override fun refreshSeries() {
        wrapWithState(
            { api.getAllSeries(100) }, dtoToEntityContainer.seriesMapper::map
        ).subscribe(
            {
                it.toData()?.let { seriesEntity ->
                    dao.insertAllSeries(seriesEntity).subscribeOn(Schedulers.io()).subscribe()
                }
            }, {
                Log.e("Repository", "Repository")
            }
        )
    }

    @SuppressLint("CheckResult")
    override fun refreshSlider() {
        wrapWithState(
            { api.getAllEvents(100) }, dtoToEntityContainer.eventMapper::map
        ).subscribe(
            {
                it.toData()?.let { eventEntity ->
                    dao.insertAllEvents(eventEntity).subscribeOn(Schedulers.io()).subscribe()
                }
            }, {
                Log.e("Repository", "Repository")
            }
        )
    }

//endregion

    //region cashing (get from Room DB)
    override fun getAllCharactersFromDB(): Observable<List<Character>> =
        dao.getAllCharacters().map { entityToDomainContainer.characterMapper.map(it) }

    override fun getAllSeriesFromDB(): Observable<List<Series>> = dao.getAllSeries().map {
        entityToDomainContainer.seriesMapper.map(it)
    }

    override fun getAllComicsFromDB(): Observable<List<Comic>> =
        dao.getAllComics().map { entityToDomainContainer.comicMapper.map(it) }


    override fun getAllEventsFromDB(): Observable<List<Event>> =
        dao.getAllEvents().map { entityToDomainContainer.eventMapper.map(it) }

    //endregion
    private fun <I, O> wrapWithState(
        function: () -> Single<Response<BaseResponse<I>>>,
        map: (List<I>) -> O,
    ): Single<UiState<O>> {
        return function().map {
            if (it.isSuccessful) {
                UiState.Success(map(it.body()?.data?.results ?: emptyList()))
            } else {
                UiState.Error(it.message())
            }
        }
    }
}

