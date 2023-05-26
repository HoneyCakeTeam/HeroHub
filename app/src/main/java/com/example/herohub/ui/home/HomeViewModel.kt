package com.example.herohub.ui.home

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.herohub.data.repository.MarvelRepository
import com.example.herohub.domain.model.Character
import com.example.herohub.domain.model.Comic
import com.example.herohub.domain.model.Event
import com.example.herohub.domain.model.Series
import com.example.herohub.ui.base.BaseViewModel
import com.example.herohub.ui.home.adapter.MostPopularComicsInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularEventsInteractionListener
import com.example.herohub.ui.home.adapter.MostPopularSeriesInteractionListener
import com.example.herohub.ui.home.adapter.SliderInteractionListener
import com.example.herohub.ui.home.adapter.SuperHeroesInteractionListener
import com.example.herohub.ui.utils.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val marvelRepositoryImp: MarvelRepository) :
    BaseViewModel(), MostPopularSeriesInteractionListener,
    SuperHeroesInteractionListener,
    SliderInteractionListener, MostPopularEventsInteractionListener,
    MostPopularComicsInteractionListener {

    private lateinit var state: Parcelable
    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

    override val TAG: String
        get() = "TAG"

    private val _homeItems = mutableListOf<HomeItem>()

    private val _characterResponse =
        MutableLiveData<List<Character>>()
    val characterResponse: LiveData<List<Character>>
        get() = _characterResponse

    private val _homeUiEvent = MutableLiveData<EventHandler<HomeUiEvent?>>(EventHandler(null))
    val homeUIEvent: LiveData<EventHandler<HomeUiEvent?>>
        get() = _homeUiEvent

    private val _seriesResponse = MutableLiveData<List<Series>>()

    private val _eventResponse = MutableLiveData<List<Event>>()

    private val _comicsResponse = MutableLiveData<List<Comic>>()

    private val _homeItemsLiveData = MutableLiveData<List<HomeItem>>()
    val homeItemsLiveData: LiveData<List<HomeItem>>
        get() = _homeItemsLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.refreshSlider()
            marvelRepositoryImp.refreshCharacters()
            marvelRepositoryImp.refreshSeries()
            marvelRepositoryImp.refreshComics()
            marvelRepositoryImp.refreshEvents()
        }
        Log.e(TAG, "init: ")
        getFromLocal()
    }

    private fun getFromLocal() {
        getSliderFromDB()
        getCharactersFromDB()
        getSeriesFromDB()
        getComicsFromDB()
        getEventsFromDB()
    }

    private fun getCharactersFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllCharactersFromDB().collectLatest {
                onGetCharacterSuccess(it)
            }
        }
    }

    private fun onGetCharacterSuccess(state: List<Character>) {
        _characterResponse.postValue(state)
        _homeItems.removeAll(listOf(HomeItem.SuperHeroes(state)))
        _homeItems.addAll(listOf(HomeItem.SuperHeroes(state)))
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun getSeriesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllSeriesFromDB().collectLatest {
                onGetSeriesSuccess(it)
            }
        }

    }

    private fun onGetSeriesSuccess(state: List<Series>) {
        _seriesResponse.postValue(state)
        _homeItems.removeAll(
            listOf(
                HomeItem.MostPopularSeries(state)
            )
        )
        _homeItems.addAll(listOf(HomeItem.MostPopularSeries(state)))
        _homeItemsLiveData.postValue(_homeItems)
    }


    private fun getComicsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllComicsFromDB().collectLatest {
                onGetComicsSuccess(it)
            }
        }


    }

    private fun onGetComicsSuccess(state: List<Comic>) {
        _comicsResponse.postValue(state)
        _homeItems.removeAll(
            listOf(
                HomeItem.MostPopularComics(state)
            )
        )
        _homeItems.addAll(
            listOf(
                HomeItem.MostPopularComics(state)
            )
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun getEventsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllEventsFromDB().collectLatest {
                onGetEventsSuccess(it)
            }
        }
    }

    private fun onGetEventsSuccess(state: List<Event>) {
        _eventResponse.postValue(state)
        _homeItems.removeAll(
            listOf(
                HomeItem.MostPopularEvents(state)
            )
        )
        _homeItems.addAll(
            listOf(
                HomeItem.MostPopularEvents(state)
            )
        )
        _homeItemsLiveData.postValue(_homeItems)
    }


    private fun getSliderFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepositoryImp.getAllEventsFromDB().collectLatest {
                onGetSliderSuccess(it)
                Log.e("Slider", it.toString())
            }
        }
    }

    private fun onGetSliderSuccess(state: List<Event>) {
        _eventResponse.postValue(state)
        _homeItems.removeAll(
            listOf(
                HomeItem.Slider(state)
            )
        )
        _homeItems.addAll(
            listOf(
                HomeItem.Slider(state)
            )
        )
        _homeItemsLiveData.postValue(_homeItems)
    }

    override fun onMostPopularSeriesItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickMostPopularSeriesItem(id)))
    }

    override fun onSeeAllSeriesClick() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllSeriesEvent))
    }

    override fun onSuperHeroesItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickCharacterEvent(id)))
    }

    override fun onSeeAllCharactersClicked() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllCharacterEvent))
    }

    override fun onSliderItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSliderItemEvent(id)))
    }

    override fun onMostPopularEventClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickEventEvent(id)))
    }

    override fun onClickSeeAllEvents() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllEventsEvent))
    }

    override fun onMostPopularComicsItemClick(id: Int) {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickComicEvent(id)))

    }

    override fun onCLickSeeAllComics() {
        _homeUiEvent.postValue(EventHandler(HomeUiEvent.ClickSeeAllComicsEvent))
    }

}