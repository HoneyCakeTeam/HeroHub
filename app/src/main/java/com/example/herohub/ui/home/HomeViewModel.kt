package com.example.herohub.ui.home

import android.annotation.SuppressLint
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        get() = this::class.java.simpleName.toString()

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
        getHomeData()
    }

    private fun getHomeData() {

        refreshSlider()
        refreshCharacters()
        refreshComics()
        refreshEvents()
        refreshSeries()

        getSliderFromDB()
        getCharactersFromDB()
        getSeriesFromDB()
        getComicsFromDB()
        getEventsFromDB()


    }

    private fun refreshCharacters() {
        marvelRepositoryImp.refreshCharacters()
    }

    @SuppressLint("CheckResult")
    private fun getCharactersFromDB() {
        disposeObservable(
            marvelRepositoryImp.getAllCharactersFromDB(),
            this::onGetCharacterSuccess,
            this::onError
        )
    }

    @SuppressLint("CheckResult")
    private fun onGetCharacterSuccess(state: List<Character>) {
        _characterResponse.postValue(state)
        _homeItems.removeAll(
            listOf(
                HomeItem.SuperHeroes(state)
            )
        )
        _homeItems.addAll(listOf(HomeItem.SuperHeroes(state)))
        _homeItemsLiveData.postValue(_homeItems)
    }

    private fun refreshSeries() {
        marvelRepositoryImp.refreshSeries()
    }

    @SuppressLint("CheckResult")
    private fun getSeriesFromDB() {
        disposeObservable(
            marvelRepositoryImp.getAllSeriesFromDB(),
            this::onGetSeriesSuccess,
            this::onError
        )

    }

    @SuppressLint("CheckResult")
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

    private fun refreshComics() {
        marvelRepositoryImp.refreshComics()
    }

    @SuppressLint("CheckResult")
    private fun getComicsFromDB() {
        disposeObservable(
            marvelRepositoryImp.getAllComicsFromDB(),
            this::onGetComicsSuccess,
            this::onError
        )

    }

    @SuppressLint("CheckResult")
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

    private fun refreshEvents() {
        marvelRepositoryImp.refreshEvents()
    }

    @SuppressLint("CheckResult")
    private fun getEventsFromDB() {
        disposeObservable(
            marvelRepositoryImp.getAllEventsFromDB(),
            this::onGetEventsSuccess,
            this::onError
        )

    }

    @SuppressLint("CheckResult")
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

    private fun refreshSlider() {
        marvelRepositoryImp.refreshSlider()
    }

    @SuppressLint("CheckResult")
    private fun getSliderFromDB() {
        disposeObservable(
            marvelRepositoryImp.getAllEventsFromDB(),
            this::onGetSliderSuccess,
            this::onError
        )

    }

    @SuppressLint("CheckResult")
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

    private fun onError(throwable: Throwable) {
        Log.e("TAG", "onError: ${throwable.message.toString()}")

//        _characterResponse.postValue(emptyList())
//        _seriesResponse.postValue(emptyList())
//        _eventResponse.postValue(emptyList())
//        _comicsResponse.postValue(emptyList())
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